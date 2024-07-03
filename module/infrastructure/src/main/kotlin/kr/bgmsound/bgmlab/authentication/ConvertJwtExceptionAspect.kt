package kr.bgmsound.bgmlab.authentication

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component

@Aspect
@Component
class ConvertJwtExceptionAspect(
    private val converter: JwtExceptionConverter
) {
    @Around("@within(convertJwtException) || @annotation(convertJwtException)")
    fun convertException(joinPoint: ProceedingJoinPoint, convertJwtException: ConvertJwtException): Any {
        return runCatching {
            joinPoint.proceed()
        }.getOrElse { exception ->
            throw converter.convert(exception)
        }
    }
}