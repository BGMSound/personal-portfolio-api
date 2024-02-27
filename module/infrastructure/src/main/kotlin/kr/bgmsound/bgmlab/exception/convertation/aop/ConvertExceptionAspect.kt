package kr.bgmsound.bgmlab.exception.convertation.aop

import kr.bgmsound.bgmlab.exception.convertation.ConvertException
import kr.bgmsound.bgmlab.exception.convertation.converter.ExceptionConverter
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Aspect
@Component
class ConvertExceptionAspect(
    private val applicationContext: ApplicationContext
) {
    @Suppress("UNCHECKED_CAST")
    @Around("@within(convertException) || @annotation(convertException)")
    fun convertException(joinPoint: ProceedingJoinPoint, convertException: ConvertException): Any {
        return runCatching {
            joinPoint.proceed()
        }.getOrElse { exception ->
            val converterClass = convertException.converter
            val converter = applicationContext.getBean(converterClass.java) as ExceptionConverter<Throwable>
            throw converter.convert(exception)
        }
    }
}