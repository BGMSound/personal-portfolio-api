package kr.bgmsound.bgmlab.exception

import kr.bgmsound.bgmlab.exception.code.ErrorCode

class UserNotFoundException : APIException(ErrorCode.USER_NOT_FOUND)