package kr.bgmsound.bgmlab.application.authentication.service.impl

import kr.bgmsound.bgmlab.application.TxUtil.Companion.writeWithTransaction
import kr.bgmsound.bgmlab.application.authentication.AuthenticationStrategyProvider
import kr.bgmsound.bgmlab.application.authentication.TokenProvider
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticatedUserDto
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
    private val authenticationStrategyProvider: AuthenticationStrategyProvider,
    private val userTokenRepository: UserTokenRepository
) : AuthenticationService {

    override fun login(request: AuthenticationRequestDto): AuthenticatedUserDto {
        val authenticationStrategy = authenticationStrategyProvider.getStrategy(type = request.type)
        val authenticatedUser = authenticationStrategy.authenticate(request)

        val accessToken = issueToken(type = TokenType.ACCESS, user = authenticatedUser)
        val refreshToken = issueToken(type = TokenType.REFRESH, user = authenticatedUser)

        return authenticatedUser.withToken(accessToken, refreshToken)
    }

    @Transactional(readOnly = true)
    override fun refresh(refreshToken: Token): Token {
        if (refreshToken.type != TokenType.REFRESH) {
            throw IllegalArgumentException("Token Type is not matched")
        }
        val userId = tokenProvider.extractIdFromToken(token = refreshToken.provider)
        val roles = tokenProvider.extractRolesFromToken(token = refreshToken.provider)

        if (userTokenRepository.notExists(userId = userId, token = refreshToken)) {
            throw AuthenticationFailException()
        }
        return issueToken(type = TokenType.ACCESS, userId = userId, authorities = roles)
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