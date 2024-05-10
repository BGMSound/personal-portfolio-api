package kr.bgmsound.bgmlab.error.exception

import kr.bgmsound.bgmlab.error.APIException
import kr.bgmsound.bgmlab.error.ErrorCode

class InvalidLocationFormatException : APIException(ErrorCode.INVALID_LOCATION_FORMAT)

class InvalidLinkFormatException : APIException(ErrorCode.INVALID_LINK_FORMAT)