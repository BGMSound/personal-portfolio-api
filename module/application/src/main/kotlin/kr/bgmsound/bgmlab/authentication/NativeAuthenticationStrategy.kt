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
        val user = userRepository.findByDisplayId(authentication.principal) ?: throw UserNotFoundException()
        val userAccount = userNativeAccountRepository.findByUserId(userId = user.id)!!
        if(authentication.credentials != userAccount.password) {
            throw UserNotFoundException() //TODO 비밀번호가 틀렸을 때 예외 처리 (변경 필요)
        }
        return user
    }
}