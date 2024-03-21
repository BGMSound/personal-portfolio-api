package kr.bgmsound.bgmlab.authentication.service.impl

import kr.bgmsound.bgmlab.authentication.AuthenticationStrategyBridge
import kr.bgmsound.bgmlab.authentication.TokenProvider
import kr.bgmsound.bgmlab.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.authentication.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.authentication.dto.TokenDto
import kr.bgmsound.bgmlab.authentication.service.AuthService
import kr.bgmsound.bgmlab.error.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.model.TokenType
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserTokenRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthServiceImpl(
    private val tokenProvider: TokenProvider,
    private val authenticationStrategyBridge: AuthenticationStrategyBridge,
    private val userTokenRepository: UserTokenRepository,
) : AuthService {

    override fun login(authentication: AuthenticationDto): LoggedInUserDto {
        val authenticationStrategy = authenticationStrategyBridge.getProvider(authentication.type)
        val loggedInUser = runCatching {
            authenticationStrategy.authenticate(authentication)
        }.getOrElse { throw AuthenticationFailException() }

        val token = TokenDto.of(
            issueNewToken(type = TokenType.ACCESS, user = loggedInUser),
            issueNewToken(type = TokenType.REFRESH, user = loggedInUser)
        )
        return LoggedInUserDto.of(loggedInUser, token)
    }

    @Transactional
    override fun refresh(refreshToken: Token): Token {
        val userId = tokenProvider.extractIdFromToken(token = refreshToken.provider)
        val roles = tokenProvider.extractRolesFromToken(token = refreshToken.provider)

        if (userTokenRepository.notExists(userId = userId, token = refreshToken)) {
            throw AuthenticationFailException()
        }
        return issueNewToken(type = TokenType.ACCESS, userId = userId, authorities = roles)
    }

    private fun issueNewToken(type: TokenType, user: User): Token {
        return issueNewToken(type, user.id, user.roles)
    }

    private fun issueNewToken(type: TokenType, userId: String, authorities: List<Role>): Token {
        val token = tokenProvider.createToken(type, userId, authorities)
        if (type == TokenType.REFRESH) {
            userTokenRepository.save(userId = userId, token = token)
        }
        return token
    }
}