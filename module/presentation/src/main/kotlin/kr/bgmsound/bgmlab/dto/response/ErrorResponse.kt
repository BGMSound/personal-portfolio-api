package kr.bgmsound.bgmlab.dto.response

import kr.bgmsound.bgmlab.error.ErrorCode

data class ErrorResponse(
    val code: String,
    val message: String
) {
    companion object {
        fun of(errorCode: ErrorCode): ErrorResponse {
            return ErrorResponse(errorCode.code, errorCode.message)
        }
    }
}