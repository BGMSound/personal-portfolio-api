package kr.bgmsound.bgmlab.application

import kotlin.reflect.KClass

@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConvertException(
    val converter: KClass<out ExceptionConverter>
)