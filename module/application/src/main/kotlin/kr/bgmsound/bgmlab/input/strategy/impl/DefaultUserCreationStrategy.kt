package kr.bgmsound.bgmlab.input.strategy.impl

import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.input.strategy.UserCreationStategy
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.output.identification.IdentifierGenerator
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class DefaultUserCreationStrategy(
    private val identifierGenerator: IdentifierGenerator
) : UserCreationStategy {

    override fun createNewSocialUser(socialLoginResult: SocialLoginResultDto): User {
        val displayId = "${socialLoginResult.provider}${socialLoginResult.socialId}".lowercase()
        return User(
            id = identifierGenerator.generateIdentifier(),
            displayId = displayId,
            name = displayId,
            roles = listOf(Role.NEED_SIGNUP),
            createAt = LocalDateTime.now()
        )
    }
}