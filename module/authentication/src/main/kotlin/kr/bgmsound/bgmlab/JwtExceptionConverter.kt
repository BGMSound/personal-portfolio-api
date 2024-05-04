package kr.bgmsound.bgmlab

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import kr.bgmsound.bgmlab.application.ExceptionConverter
import kr.bgmsound.bgmlab.error.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.error.exception.TokenExpiredException
import org.springframework.stereotype.Component

@Component
class JwtExceptionConverter : ExceptionConverter {
    override fun convert(exception: Throwable): Throwable {
        if (exception !is JwtException) {
            return exception
        }
        return when (exception) {
            is ExpiredJwtException -> TokenExpiredException()
            else -> AuthenticationFailException()
        }
    }
}