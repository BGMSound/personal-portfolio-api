package kr.bgmsound.bgmlab.application.authentication.service.impl

import kr.bgmsound.bgmlab.application.TxUtil.Companion.writeWithTransaction
import kr.bgmsound.bgmlab.application.authentication.AuthenticationSupportProvider
import kr.bgmsound.bgmlab.application.authentication.TokenProvider
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticatedUserDto
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationRequestDto
import kr.bgmsound.bgmlab.application.authentication.service.AuthenticationService
import kr.bgmsound.bgmlab.error.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.model.TokenType
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationServiceImpl(
    private val tokenProvider: TokenProvider,
    private val authenticationSupportProvider: AuthenticationSupportProvider,
    private val userTokenRepository: UserTokenRepository
) : AuthenticationService {

    override fun login(request: AuthenticationRequestDto): AuthenticatedUserDto {
        val authenticationSupport = authenticationSupportProvider.from(type = request.type)
        val authenticatedUser = authenticationSupport.authenticate(request)

        val accessToken = issueToken(type = TokenType.ACCESS, user = authenticatedUser)
        val refreshToken = issueToken(type = TokenType.REFRESH, user = authenticatedUser)

        return authenticatedUser.withToken(accessToken, refreshToken)
    }

    @Transactional(readOnly = true)
    override fun refresh(refreshToken: Token): Token {
        val authentication: AuthenticationDto = tokenProvider.makeAuthentication(token = refreshToken.provider)
        if (authentication.type != TokenType.REFRESH) {
            throw AuthenticationFailException()
        }

        if (userTokenRepository.notExists(userId = authentication.principal, token = refreshToken)) {
            throw AuthenticationFailException()
        }
        return issueToken(type = TokenType.ACCESS, userId = authentication.principal, authorities = authentication.roles)
    }

    private fun issueToken(type: TokenType, user: User): Token {
        return issueToken(type, user.id, user.roles)
    }

    private fun issueToken(type: TokenType, userId: String, authorities: List<Role>): Token {
        val token = tokenProvider.createToken(type, userId, authorities)
        if (type == TokenType.REFRESH) {
            writeWithTransaction {
                userTokenRepository.save(userId = userId, token = token)
            }
        }
        return token
    }

    private fun User.withToken(accessToken: Token, refreshToken: Token): AuthenticatedUserDto {
        return AuthenticatedUserDto.of(user = this, accessToken = accessToken, refreshToken = refreshToken)
    }
}