package kr.bgmsound.bgmlab

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import kr.bgmsound.bgmlab.application.authentication.TokenProvider
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.exception.conversion.ConvertException
import kr.bgmsound.bgmlab.exception.conversion.converter.JwtExceptionConverter
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.model.TokenType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec

@Component
class JwtTokenProvider(
    @Value("\${token.secret}") private val secret: String,
    @Value("\${token.expiration.access}") private val accessTokenExpiration: Long,
    @Value("\${token.expiration.refresh}") private val refreshTokenExpiration: Long
) : TokenProvider {

    private val signKey: SecretKey = SecretKeySpec(secret.toByteArray(), "HmacSHA256")

    override fun createToken(type: TokenType, id: String, authorities: List<Role>): Token {
        return when (type) {
            TokenType.ACCESS -> Token(type, createAccessToken(id, authorities))
            TokenType.REFRESH -> Token(type, createRefreshToken(id, authorities))
        }
    }

    @ConvertException(converter = JwtExceptionConverter::class)
    override fun makeAuthenticationFrom(token: String): AuthenticationDto {
        val parser: Jws<Claims> = Jwts
            .parser()
            .verifyWith(signKey)
            .build()
            .parseSignedClaims(token)

        return AuthenticationDto(
            type = parser.extractType(),
            principal = parser.extractId(),
            credentials = token,
            roles = parser.extractRoles()
        )
    }

    @ConvertException(converter = JwtExceptionConverter::class)
    override fun extractTypeFromToken(token: String): TokenType {
        return Jwts
            .parser()
            .verifyWith(signKey)
            .build()
            .parseSignedClaims(token)
            .extractType()
    }

    @ConvertException(converter = JwtExceptionConverter::class)
    override fun extractIdFromToken(token: String): String {
        return Jwts
            .parser()
            .verifyWith(signKey)
            .build()
            .parseSignedClaims(token)
            .extractId()
    }

    @ConvertException(converter = JwtExceptionConverter::class)
    override fun extractRolesFromToken(token: String): List<Role> {
        return Jwts
            .parser()
            .verifyWith(signKey)
            .build()
            .parseSignedClaims(token)
            .extractRoles()
    }

    private fun Jws<Claims>.extractType(): TokenType {
        return TokenType.valueOf(this.header["typ"].toString())
    }

    private fun Jws<Claims>.extractId(): String {
        return this.payload["id"].toString()
    }

    private fun Jws<Claims>.extractRoles(): List<Role> {
        return this.payload["roles"]
            .toString()
            .split(AUTHORITY_DELIMITER)
            .map {
                Role.valueOf(it)
            }
    }

    private fun createAccessToken(id: String, authorities: List<Role>): String {
        return Jwts
            .builder()
            .header().empty().add(buildHeader(TokenType.ACCESS)).and()
            .claims(payload(id, authorities))
            .expiration(buildAccessTokenExpiration())
            .issuedAt(Date())
            .signWith(signKey)
            .compact()
    }

    private fun createRefreshToken(id: String, authorities: List<Role>): String {
        return Jwts
            .builder()
            .header().empty().add(buildHeader(TokenType.REFRESH)).and()
            .claims(payload(id, authorities))
            .expiration(buildRefreshTokenExpiration())
            .issuedAt(Date())
            .signWith(signKey)
            .compact()
    }

    private fun buildHeader(type: TokenType) = mapOf(
        "tok" to "JWT",
        "typ" to type.name,
        "alg" to "HS256",
        "regDate" to System.currentTimeMillis()
    )

    private fun payload(id: String, authorities: List<Role>) = mapOf(
        "id" to id,
        "roles" to authorities.joinTo(StringBuilder(), AUTHORITY_DELIMITER) { it.name }
    )

    private fun buildAccessTokenExpiration() = Date(System.currentTimeMillis() + accessTokenExpiration * 1000)

    private fun buildRefreshTokenExpiration() = Date(System.currentTimeMillis() + refreshTokenExpiration * 1000)

    companion object {
        private const val AUTHORITY_DELIMITER = "::"
    }
}