package kr.bgmsound.bgmlab.error

open class APIException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)