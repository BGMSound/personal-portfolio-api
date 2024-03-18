package kr.bgmsound.bgmlab.service

import kr.bgmsound.bgmlab.adapter.authentication.TokenProvider
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.model.TokenType
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserTokenRepository

abstract class AbstractAuthService(
    private val tokenProvider: TokenProvider,
    private val userTokenRepository: UserTokenRepository,
) : AuthService {

    override fun refresh(refreshToken: Token): Token {
        val userId = tokenProvider.extractIdFromToken(token = refreshToken.provider)
        val roles = tokenProvider.extractRolesFromToken(token = refreshToken.provider)

        return issueNewToken(type = TokenType.ACCESS, userId = userId, authorities = roles)
    }

    protected fun issueNewToken(type: TokenType, user: User): Token {
        return issueNewToken(type, user.id, user.roles)
    }

    protected fun issueNewToken(type: TokenType, userId: String, authorities: List<Role>): Token {
        val token = tokenProvider.createToken(type, userId, authorities)
        if(type == TokenType.REFRESH) {
            userTokenRepository.save(userId = userId, token = token)
        }
        return token
    }
}