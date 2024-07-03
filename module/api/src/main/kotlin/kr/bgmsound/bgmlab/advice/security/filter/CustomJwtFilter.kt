package kr.bgmsound.bgmlab.advice.security.filter

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.bgmsound.bgmlab.advice.APIAuthentication
import kr.bgmsound.bgmlab.advice.httpStatus
import kr.bgmsound.bgmlab.application.authentication.TokenProvider
import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticationDto
import kr.bgmsound.bgmlab.shared.getLogger
import kr.bgmsound.bgmlab.error.APIException
import kr.bgmsound.bgmlab.error.ErrorCode
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.shared.ErrorResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.MediaType
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class CustomJwtFilter(
    @Value("\${app.headers.auth-token}") private val authTokenHeader: String,
    private val tokenProvider: TokenProvider,
    private val objectMapper: ObjectMapper
) : OncePerRequestFilter() {
    private val log = getLogger<CustomJwtFilter>()

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader(authTokenHeader)
        if(token == null) {
            filterChain.doFilter(request, response)
            return
        }
        try {
            SecurityContextHolder.getContext().authentication = authentication(token = token)
            filterChain.doFilter(request, response)
        } catch (exception: APIException) {
            log.error(exception.message)
            writeErrorResponse(response, exception.errorCode)
        }
    }

    private fun authentication(token: String): Authentication {
        val authentication: AuthenticationDto = tokenProvider.makeAuthentication(token)
        return APIAuthentication.of(
            userId = authentication.principal,
            accessToken = token,
            roles = authentication.roles.toGrantedAuthorities()
        )
    }

    private fun List<Role>.toGrantedAuthorities(): List<SimpleGrantedAuthority> {
        return this.map {
            SimpleGrantedAuthority("ROLE_${it}")
        }
    }

    private fun writeErrorResponse(response: HttpServletResponse, errorCode: ErrorCode) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.status = errorCode.httpStatus()
        response.writer.write(
            objectMapper.writeValueAsString(ErrorResponse.of(errorCode))
        )
    }
}