package kr.bgmsound.bgmlab.exception.conversion.converter

interface ExceptionConverter {

    fun convert(exception: Throwable): Throwable

}