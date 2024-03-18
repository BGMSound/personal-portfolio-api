package kr.bgmsound.bgmlab.service.impl

import kr.bgmsound.bgmlab.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.error.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.strategy.LoginProviderType
import kr.bgmsound.bgmlab.adapter.authentication.LoginProviderManager
import kr.bgmsound.bgmlab.adapter.authentication.TokenProvider
import kr.bgmsound.bgmlab.adapter.account.UserSocialAccountRepository
import kr.bgmsound.bgmlab.dto.AuthenticationDto
import kr.bgmsound.bgmlab.dto.TokenDto
import kr.bgmsound.bgmlab.dto.account.SocialAccount
import kr.bgmsound.bgmlab.error.exception.UserNotFoundException
import kr.bgmsound.bgmlab.model.TokenType
import kr.bgmsound.bgmlab.repository.UserRepository
import kr.bgmsound.bgmlab.repository.UserTokenRepository
import kr.bgmsound.bgmlab.service.AbstractAuthService
import kr.bgmsound.bgmlab.strategy.UserCreationStrategy
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class OAuthService(
    tokenProvider: TokenProvider,
    userTokenRepository: UserTokenRepository,

    private val loginProviderManager: LoginProviderManager,
    private val userCreationStrategy: UserCreationStrategy,
    private val userRepository: UserRepository,
    private val userSocialAccountRepository: UserSocialAccountRepository
) : AbstractAuthService(tokenProvider, userTokenRepository) {

    @Transactional
    override fun login(authentication: AuthenticationDto): LoggedInUserDto {
        val provider = loginProviderManager.getSocialLoginProvider(authentication.type)
        val loginResult = runCatching { provider.login(authentication.credentials) }.getOrElse { throw AuthenticationFailException() }

        val socialUser = userSocialAccountRepository
            .findBySocialId(provider = loginResult.provider, socialId = loginResult.socialId)
            ?.let {
                userRepository.findByAccount(account = it) ?: throw UserNotFoundException()
            }
            ?: registerNewSocialUser(loginResult = loginResult)

        val token = TokenDto.of(
            issueNewToken(type = TokenType.ACCESS, user = socialUser),
            issueNewToken(type = TokenType.REFRESH, user = socialUser)
        )
        return LoggedInUserDto.of(socialUser, token)
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