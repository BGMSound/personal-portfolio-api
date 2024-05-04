package kr.bgmsound.bgmlab.application

interface ExceptionConverter {

    fun convert(exception: Throwable): Throwable

}