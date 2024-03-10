package kr.bgmsound.bgmlab.strategy.impl

import kr.bgmsound.bgmlab.strategy.UserCreationStrategy
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.strategy.LoginProviderType
import kr.bgmsound.bgmlab.adapter.identification.IdentifierGenerator
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DefaultUserCreationStrategy(
    private val identifierGenerator: IdentifierGenerator
) : UserCreationStrategy {

    override fun createNewUser(provider: LoginProviderType, id: String): User {
        val displayId = if(provider == LoginProviderType.NATIVE) { id } else { "${provider.name}$id".lowercase() }

        return User(
            id = identifierGenerator.generateIdentifier(),
            displayId = displayId,
            name = displayId,
            roles = listOf(Role.NEED_SIGNUP),
            createAt = LocalDateTime.now()
        )
    }
}