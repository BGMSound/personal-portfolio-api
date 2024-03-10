package kr.bgmsound.bgmlab.error.exception

import kr.bgmsound.bgmlab.error.APIException
import kr.bgmsound.bgmlab.error.ErrorCode

class TokenExpiredException : APIException(ErrorCode.TOKEN_EXPIRED)

class AuthenticationFailException : APIException(ErrorCode.AUTHENTICATION_FAIL)

class InvalidException : APIException(ErrorCode.INVALID_ERROR)