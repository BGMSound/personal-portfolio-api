package kr.bgmsound.bgmlab.advice.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.bgmsound.bgmlab.advice.security.APIAuthentication
import kr.bgmsound.bgmlab.output.authentication.TokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class CustomJwtFilter(
    @Value("\${app.headers.auth-token}") private val authTokenHeader: String,
    private val tokenProvider: TokenProvider,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader(authTokenHeader)
        if (token != null) {
            val authentication = APIAuthentication.of(
                userId = tokenProvider.extractIdFromToken(token),
                accessToken = token,
                roles = tokenProvider.extractRolesFromToken(token)
                    .map {
                        SimpleGrantedAuthority("ROLE_${it}")
                    }
                    .toMutableList()
            )
            SecurityContextHolder.getContext().authentication = authentication
        }
        filterChain.doFilter(request, response)
    }
}