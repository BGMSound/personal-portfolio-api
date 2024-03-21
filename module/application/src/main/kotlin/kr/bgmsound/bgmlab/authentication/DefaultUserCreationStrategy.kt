package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.IdentifierGenerator
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.User
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DefaultUserCreationStrategy(
    private val identifierGenerator: IdentifierGenerator
) : UserCreationStrategy {

    override fun createNewUser(provider: LoginType, id: String): User {
        val displayId = "${provider.name}$id".lowercase()

        return User(
            id = identifierGenerator.generateIdentifier(),
            displayId = displayId,
            name = displayId,
            roles = listOf(Role.NEED_SIGNUP),
            createAt = LocalDateTime.now()
        )
    }
}