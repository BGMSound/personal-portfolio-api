package kr.bgmsound.bgmlab.exception

import kr.bgmsound.bgmlab.exception.code.ErrorCode

open class APIException(val errorCode: ErrorCode) : RuntimeException()