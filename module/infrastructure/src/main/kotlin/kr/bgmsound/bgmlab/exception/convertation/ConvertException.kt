package kr.bgmsound.bgmlab.exception.convertation


import kr.bgmsound.bgmlab.exception.convertation.converter.ExceptionConverter
import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConvertException(
    val converter: KClass<out ExceptionConverter<*>>
)