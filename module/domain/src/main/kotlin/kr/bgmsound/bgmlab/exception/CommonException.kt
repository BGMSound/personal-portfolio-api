package kr.bgmsound.bgmlab.exception

import kr.bgmsound.bgmlab.exception.code.ErrorCode

class TokenExpiredException : APIException(ErrorCode.TOKEN_EXPIRED)

class AuthenticationFailException : APIException(ErrorCode.AUTHENTICATION_FAIL)

class InvalidException : APIException(ErrorCode.INVALID_ERROR)