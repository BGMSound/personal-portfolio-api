package kr.bgmsound.bgmlab.exception.convertation.converter

interface ExceptionConverter {

    fun convert(exception: Throwable): Throwable

}