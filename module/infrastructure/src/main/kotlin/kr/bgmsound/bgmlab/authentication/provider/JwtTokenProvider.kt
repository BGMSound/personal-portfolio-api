package kr.bgmsound.bgmlab.authentication.provider

import io.jsonwebtoken.Jwts
import kr.bgmsound.bgmlab.authentication.converter.JwtExceptionConverter
import kr.bgmsound.bgmlab.exception.convertation.ConvertException
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.adapter.authentication.TokenProvider
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
        return when(type) {
            TokenType.ACCESS -> Token(type, createAccessToken(id, authorities))
            TokenType.REFRESH -> Token(type, createRefreshToken(id, authorities))
        }
    }

    private fun createAccessToken(id: String, authorities: List<Role>): String {
        return Jwts
            .builder()
            .header().empty().add(buildHeader()).and()
            .claims(payload(id, authorities))
            .expiration(buildAccessTokenExpiration())
            .issuedAt(Date())
            .signWith(signKey)
            .compact()
    }

    private fun createRefreshToken(id: String, authorities: List<Role>): String {
        return Jwts
            .builder()
            .header().empty().add(buildHeader()).and()
            .claims(payload(id, authorities))
            .expiration(buildRefreshTokenExpiration())
            .issuedAt(Date())
            .signWith(signKey)
            .compact()
    }

    @ConvertException(converter = JwtExceptionConverter::class)
    override fun extractIdFromToken(token: String): String {
        return Jwts
            .parser()
            .verifyWith(signKey)
            .build()
            .parseSignedClaims(token)
            .payload["id"]
            .toString()
    }

    @ConvertException(converter = JwtExceptionConverter::class)
    override fun extractRolesFromToken(token: String): List<Role> {
        return Jwts
            .parser()
            .verifyWith(signKey)
            .build()
            .parseSignedClaims(token)
            .payload["roles"]
            .toString()
            .split("::")
            .map { Role.valueOf(it) }
    }

    private fun buildHeader() = mapOf(
        "typ" to "JWT",
        "alg" to "HS256",
        "regDate" to System.currentTimeMillis()
    )

    private fun payload(id: String, authorities: List<Role>) = mapOf(
        "id" to id,
        "roles" to authorities.joinTo(StringBuilder(), "::") { it.name }
    )

    private fun buildAccessTokenExpiration() = Date(System.currentTimeMillis() + accessTokenExpiration * 1000)

    private fun buildRefreshTokenExpiration() = Date(System.currentTimeMillis() + refreshTokenExpiration * 1000)
}