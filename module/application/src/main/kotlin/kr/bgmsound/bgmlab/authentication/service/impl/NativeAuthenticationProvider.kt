package kr.bgmsound.bgmlab.authentication.service.impl

import kr.bgmsound.bgmlab.authentication.AuthenticationProvider
import kr.bgmsound.bgmlab.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.authentication.repository.UserNativeAccountRepository
import kr.bgmsound.bgmlab.model.User
import org.springframework.stereotype.Component

@Component
class NativeAuthenticationProvider(
    private val userNativeAccountRepository: UserNativeAccountRepository,
) : AuthenticationProvider {

    override fun authenticate(authentication: AuthenticationDto): User {
        TODO("Not yet implemented")
    }
}