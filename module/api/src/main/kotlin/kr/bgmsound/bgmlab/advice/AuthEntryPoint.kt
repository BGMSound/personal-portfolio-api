package kr.bgmsound.bgmlab.advice

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import kr.bgmsound.bgmlab.error.ErrorCode
import kr.bgmsound.bgmlab.shared.ErrorResponse
import org.springframework.http.MediaType
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class AuthEntryPoint(
    private val objectMapper: ObjectMapper
) : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        writeErrorResponse(response)
    }

    private fun writeErrorResponse(response: HttpServletResponse) {
        response.contentType = MediaType.APPLICATION_JSON_VALUE
        response.characterEncoding = "UTF-8"
        response.status = ErrorCode.NOT_AUTHORIZED.httpStatus()
        response.writer.write(
            objectMapper.writeValueAsString(ErrorResponse.of(ErrorCode.NOT_AUTHORIZED))
        )
    }
}