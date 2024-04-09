package kr.bgmsound.bgmlab.application.authentication

import kr.bgmsound.bgmlab.IdentifierGenerator
import kr.bgmsound.bgmlab.application.TxUtil.Companion.writeWithTransaction
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.error.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.error.exception.UserNotFoundException
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserRepository
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class OAuthAuthenticationStrategy(
    private val oAuthGatewayFactory: OAuthGatewayFactory,
    private val identifierGenerator: IdentifierGenerator,
    private val userRepository: UserRepository,
    private val userSocialAccountRepository: UserSocialAccountRepository
) : AuthenticationStrategy {

    override fun authenticate(authentication: AuthenticationDto): User {
        val oAuthGateway = oAuthGatewayFactory.of(authentication.principal)
        val result = runCatching {
            oAuthGateway.authenticate(authentication.credentials)
        }.getOrElse {
            throw AuthenticationFailException()
        }

        return writeWithTransaction {
            userSocialAccountRepository
                .findBySocialId(result.provider, result.socialId)
                ?.let { userRepository.findById(it.userId) ?: throw UserNotFoundException() }
                ?: registerNewUser(result)
        }
    }

    private fun registerNewUser(loginResult: OAuthResult): User {
        val user = createNewUser(loginResult.provider, loginResult.socialId)
        val account = SocialAccount.of(user = user, provider = loginResult.provider, socialId = loginResult.socialId)

        userRepository.save(user)
        userSocialAccountRepository.save(account)
        return user
    }

    private fun createNewUser(provider: String, id: String): User {
        val suffix = identifierGenerator.generateIdentifier()
        val displayId = "${provider}${id}${suffix.subSequence(0, 15)}".lowercase()

        return User(
            id = identifierGenerator.generateIdentifier(),
            displayId = displayId,
            name = displayId,
            roles = listOf(Role.NEED_SIGNUP),
            createAt = LocalDateTime.now()
        )
    }
}