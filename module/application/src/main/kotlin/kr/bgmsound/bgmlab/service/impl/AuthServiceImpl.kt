package kr.bgmsound.bgmlab.service.impl

import kr.bgmsound.bgmlab.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.dto.TokenDto
import kr.bgmsound.bgmlab.error.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.strategy.LoginProviderType
import kr.bgmsound.bgmlab.adapter.authentication.LoginProviderManager
import kr.bgmsound.bgmlab.adapter.authentication.TokenProvider
import kr.bgmsound.bgmlab.adapter.account.UserSocialAccountRepository
import kr.bgmsound.bgmlab.dto.account.SocialAccount
import kr.bgmsound.bgmlab.error.exception.UserNotFoundException
import kr.bgmsound.bgmlab.repository.UserRepository
import kr.bgmsound.bgmlab.service.AuthService
import kr.bgmsound.bgmlab.strategy.UserCreationStrategy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    private val loginProviderManager: LoginProviderManager,
    private val tokenProvider: TokenProvider,
    private val userCreationStrategy: UserCreationStrategy,

    private val userRepository: UserRepository,
    private val userSocialAccountRepository: UserSocialAccountRepository
) : AuthService {

    @Transactional
    override fun socialLogin(type: LoginProviderType, code: String): LoggedInUserDto {
        val provider = loginProviderManager.getSocialLoginProvider(type)
        val loginResult = runCatching { provider.login(code) }.getOrElse { throw AuthenticationFailException() }

        val socialUser = userSocialAccountRepository
            .findBySocialId(provider = loginResult.provider, socialId = loginResult.socialId)
            ?.let {
                userRepository.findByAccount(account = it) ?: throw UserNotFoundException()
            }
            ?: registerNewSocialUser(loginResult = loginResult)

        val token = issueToken(socialUser)
        return LoggedInUserDto.of(socialUser, token)
    }

    private fun issueToken(user: User): TokenDto {
        return TokenDto.of(
            accessToken = tokenProvider.createAccessToken(id = user.id, authorities = user.roles),
            refreshToken = tokenProvider.createRefreshToken(id = user.id, authorities = user.roles)
        )
    }

    private fun registerNewSocialUser(loginResult: SocialLoginResultDto): User {
        if(loginResult.provider == LoginProviderType.NATIVE) {
            throw IllegalArgumentException("Native login is not supported in social login.")
        }
        val user = userCreationStrategy.createNewUser(loginResult.provider, loginResult.socialId)
        val account = SocialAccount.of(user = user, provider = loginResult.provider, socialId = loginResult.socialId)

        userRepository.save(user)
        userSocialAccountRepository.save(account)
        return user
    }

}