package kr.bgmsound.bgmlab.error.exception

import kr.bgmsound.bgmlab.error.APIException
import kr.bgmsound.bgmlab.error.ErrorCode

class UserNotFoundException : APIException(ErrorCode.USER_NOT_FOUND)

class UserAlreadyExistsException : APIException(ErrorCode.USER_ALREADY_EXISTS)