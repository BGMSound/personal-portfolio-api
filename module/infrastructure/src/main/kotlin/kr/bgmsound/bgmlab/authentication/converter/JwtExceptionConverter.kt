package kr.bgmsound.bgmlab.authentication.converter

import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import kr.bgmsound.bgmlab.error.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.error.exception.TokenExpiredException
import kr.bgmsound.bgmlab.exception.convertation.converter.ExceptionConverter
import org.springframework.stereotype.Component

@Component
class JwtExceptionConverter : ExceptionConverter {
    override fun convert(exception: Throwable): Throwable {
        if(exception !is JwtException) {
            return exception
        }
        return when (exception) {
            is ExpiredJwtException -> TokenExpiredException()
            else -> AuthenticationFailException()
        }
    }
}