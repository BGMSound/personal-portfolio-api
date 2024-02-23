package kr.bgmsound.bgmlab.input.strategy.impl

import kr.bgmsound.bgmlab.input.strategy.UserCreationStrategy
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.output.authentication.LoginProviderType
import kr.bgmsound.bgmlab.output.identification.IdentifierGenerator
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DefaultUserCreationStrategy(
    private val identifierGenerator: IdentifierGenerator
) : UserCreationStrategy {

    override fun createNewSocialUser(provider: LoginProviderType, socialId: String): User {
        val displayId = "${provider}${socialId}".lowercase()
        return User(
            id = identifierGenerator.generateIdentifier(),
            displayId = displayId,
            name = displayId,
            roles = listOf(Role.NEED_SIGNUP),
            createAt = LocalDateTime.now()
        )
    }
}