package kr.bgmsound.bgmlab

import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.authentication.dto.LoginType
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DefaultUserCreationStrategy(
    private val identifierGenerator: IdentifierGenerator
) : UserCreationStrategy {

    override fun createNewUser(provider: LoginType, id: String): User {
        val displayId = if(provider == LoginType.NATIVE) { id } else { "${provider.name}$id".lowercase() }

        return User(
            id = identifierGenerator.generateIdentifier(),
            displayId = displayId,
            name = displayId,
            roles = listOf(Role.NEED_SIGNUP),
            createAt = LocalDateTime.now()
        )
    }
}