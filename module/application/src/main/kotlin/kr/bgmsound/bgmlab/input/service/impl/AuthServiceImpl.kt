package kr.bgmsound.bgmlab.input.service.impl

import kr.bgmsound.bgmlab.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.dto.TokenDto
import kr.bgmsound.bgmlab.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.input.service.AuthService
import kr.bgmsound.bgmlab.input.strategy.UserCreationStrategy
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.output.authentication.LoginProviderType
import kr.bgmsound.bgmlab.output.authentication.LoginProviderManager
import kr.bgmsound.bgmlab.output.authentication.TokenProvider
import kr.bgmsound.bgmlab.repository.UserRepository
import kr.bgmsound.bgmlab.repository.UserSocialAccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    private val loginProviderManager: LoginProviderManager,
    private val tokenProvider: TokenProvider,
    private val userCreationStrategy: UserCreationStrategy,

    private val userRepository: UserRepository,
    private val userSocialAccountRepository: UserSocialAccountRepository,
) : AuthService {

    @Transactional
    override fun socialLogin(type: LoginProviderType, code: String): LoggedInUserDto {
        val provider = loginProviderManager.getSocialLoginProvider(type)
        val loginResult = runCatching {
            provider.login(code)
        }.getOrElse { throw AuthenticationFailException() }

        val socialUser = userSocialAccountRepository
            .findBySocialId(provider = type.name, socialId = loginResult.socialId)
            ?: registerAndGetNewSocialUserByLoginResult(loginResult)

        val token = issueToken(socialUser)
        return LoggedInUserDto.newInstance(socialUser, token)
    }

    private fun issueToken(user: User): TokenDto {
        return TokenDto.of(
            accessToken = tokenProvider.createAccessToken(user.id, user.roles),
            refreshToken = tokenProvider.createRefreshToken(user.id, user.roles)
        )
    }

    private fun registerAndGetNewSocialUserByLoginResult(loginResult: SocialLoginResultDto): User {
        if (loginResult.provider == LoginProviderType.NATIVE) {
            throw IllegalArgumentException("Native login is not supported")
        }
        val user = userCreationStrategy.createNewUser(loginResult.provider, loginResult.socialId)
        userRepository.save(user)
        userSocialAccountRepository.save(user.id, createSocialAccount(loginResult))
        return user
    }

    private fun createSocialAccount(loginResult: SocialLoginResultDto): User.SocialAccount {
        return User.SocialAccount(provider = loginResult.provider.name, socialId = loginResult.socialId)
    }
}