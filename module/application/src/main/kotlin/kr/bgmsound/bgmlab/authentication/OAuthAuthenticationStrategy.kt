package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.UserCreationStrategy
import kr.bgmsound.bgmlab.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.authentication.dto.LoginType
import kr.bgmsound.bgmlab.authentication.dto.OAuthResult
import kr.bgmsound.bgmlab.authentication.dto.SocialAccount
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class OAuthAuthenticationStrategy(
    private val oAuthGatewayFactory: OAuthGatewayFactory,
    private val userCreationStrategy: UserCreationStrategy,

    private val userRepository: UserRepository,
    private val userSocialAccountRepository: UserSocialAccountRepository
) : AuthenticationStrategy {

    override fun authenticate(authentication: AuthenticationDto): User {
        val oAuthGateway = oAuthGatewayFactory.of(authentication.type)
        val result = oAuthGateway.authenticate(authentication.credentials)

        return userSocialAccountRepository
            .findBySocialId(result.provider, result.socialId)
            ?.let { userRepository.findById(it.userId) }
            ?: registerNewUser(result)
    }

    private fun registerNewUser(loginResult: OAuthResult): User {
        if(loginResult.provider == LoginType.NATIVE) {
            throw IllegalArgumentException("Native login is not supported in social login.")
        }
        val user = userCreationStrategy.createNewUser(loginResult.provider, loginResult.socialId)
        val account = SocialAccount.of(user = user, provider = loginResult.provider, socialId = loginResult.socialId)

        userRepository.save(user)
        userSocialAccountRepository.save(account)
        return user
    }
}