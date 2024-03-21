package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.error.exception.UserNotFoundException
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class NativeAuthenticationStrategy(
    private val userRepository: UserRepository,
    private val userNativeAccountRepository: UserNativeAccountRepository,
) : AuthenticationStrategy {

    @Transactional(readOnly = true)
    override fun authenticate(authentication: AuthenticationDto): User {
        val userAccount = userNativeAccountRepository
            .findByDisplayId(authentication.principal)
            ?: throw UserNotFoundException()

        val user = userRepository.findById(userAccount.userId)!!

        return user
    }
}