package kr.bgmsound.bgmlab.application.authentication

import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationRequestDto
import kr.bgmsound.bgmlab.error.exception.UserNotFoundException
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserRepository
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class NativeAuthenticationStrategy(
    private val userRepository: UserRepository,
    private val userNativeAccountRepository: UserNativeAccountRepository
) : AuthenticationStrategy {

    @Transactional(readOnly = true)
    override fun authenticate(authentication: AuthenticationRequestDto): User {
        val user = userRepository.findByDisplayId(authentication.principal) ?: throw UserNotFoundException()
        val userAccount = userNativeAccountRepository.findByUserId(userId = user.id)!!
        //TODO Password Encoder 적용 필요
        if (authentication.credentials != userAccount.password) {
            throw UserNotFoundException() //TODO 비밀번호가 틀렸을 때 예외 처리 (변경 필요)
        }
        return user
    }

    override fun getType(): AuthenticationType {
        return AuthenticationType.NATIVE
    }
}