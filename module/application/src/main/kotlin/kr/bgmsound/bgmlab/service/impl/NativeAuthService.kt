package kr.bgmsound.bgmlab.service.impl

import kr.bgmsound.bgmlab.adapter.account.UserNativeAccountRepository
import kr.bgmsound.bgmlab.adapter.authentication.TokenProvider
import kr.bgmsound.bgmlab.dto.AuthenticationDto
import kr.bgmsound.bgmlab.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.repository.UserRepository
import kr.bgmsound.bgmlab.repository.UserTokenRepository
import kr.bgmsound.bgmlab.service.AbstractAuthService
import org.springframework.stereotype.Service

@Service
class NativeAuthService(
    tokenProvider: TokenProvider,
    userTokenRepository: UserTokenRepository,

    private val userRepository: UserRepository,
    private val userNativeAccountRepository: UserNativeAccountRepository,
) : AbstractAuthService(tokenProvider, userTokenRepository) {

    override fun login(authentication: AuthenticationDto): LoggedInUserDto {
        TODO("Not yet implemented")
    }
}