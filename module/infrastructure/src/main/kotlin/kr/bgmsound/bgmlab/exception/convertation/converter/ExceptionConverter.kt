package kr.bgmsound.bgmlab.exception.convertation.converter

interface ExceptionConverter<E : Throwable> {

    fun convert(exception: E): Throwable

}