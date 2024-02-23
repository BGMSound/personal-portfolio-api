package kr.bgmsound.bgmlab.input.service.impl

import kr.bgmsound.bgmlab.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.dto.TokenDto
import kr.bgmsound.bgmlab.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.input.service.AuthService
import kr.bgmsound.bgmlab.input.strategy.UserCreationStrategy
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.output.authentication.LoginProviderType
import kr.bgmsound.bgmlab.output.authentication.SocialLoginProvider
import kr.bgmsound.bgmlab.output.authentication.TokenProvider
import kr.bgmsound.bgmlab.repository.UserRepository
import kr.bgmsound.bgmlab.repository.UserSocialAccountRepository
import org.springframework.beans.factory.ObjectProvider
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    private val loginProviders: ObjectProvider<SocialLoginProvider>,
    private val tokenProvider: TokenProvider,
    private val userCreationStrategy: UserCreationStrategy,

    private val userRepository: UserRepository,
    private val userSocialAccountRepository: UserSocialAccountRepository,
) : AuthService {

    @Transactional
    override fun socialLogin(type: LoginProviderType, code: String): LoggedInUserDto {
        val provider = loginProviders.find { provider -> provider.getType() == type } ?: throw IllegalArgumentException(
            "Invalid login provider"
        )
        val loginResult = runCatching { provider.login(code) }.getOrElse { throw AuthenticationFailException() }

        val socialUser = userSocialAccountRepository
            .findBySocialId(provider = type.name, socialId = loginResult.socialId)
            ?: registerAndGetNewSocialUser(loginResult)

        val token = TokenDto.of(
            accessToken = tokenProvider.createAccessToken(socialUser.id, socialUser.roles),
            refreshToken = tokenProvider.createRefreshToken(socialUser.id, socialUser.roles)
        )
        return LoggedInUserDto.newInstance(socialUser, token)
    }

    private fun registerAndGetNewSocialUser(loginResult: SocialLoginResultDto): User {
        if (loginResult.provider == LoginProviderType.NATIVE) {
            throw IllegalArgumentException("Native login is not supported")
        }
        val user = userCreationStrategy.createNewSocialUser(loginResult)
        userRepository.save(user)
        userSocialAccountRepository.save(user.id, createSocialAccount(loginResult))
        return user
    }

    private fun createSocialAccount(loginResult: SocialLoginResultDto): User.SocialAccount {
        return User.SocialAccount(provider = loginResult.provider.name, socialId = loginResult.socialId)
    }
}