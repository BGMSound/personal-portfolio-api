package kr.bgmsound.bgmlab.exception.conversion

interface ExceptionConverter {

    fun convert(exception: Throwable): Throwable

}