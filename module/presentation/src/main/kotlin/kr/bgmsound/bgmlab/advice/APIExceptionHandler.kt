package kr.bgmsound.bgmlab.advice

import kr.bgmsound.bgmlab.dto.response.ErrorResponse
import kr.bgmsound.bgmlab.exception.APIException
import kr.bgmsound.bgmlab.exception.code.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class APIExceptionHandler {

    @ExceptionHandler(value = [APIException::class])
    fun handleFriendAddSelfException(exception: APIException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(exception.errorCode.httpStatus)
            .body(
                errorBody(exception.errorCode)
            )
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleInvalidException(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errorCodes = exception.bindingResult.fieldErrors.map {
            ErrorCode.valueOf(it.defaultMessage ?: ErrorCode.UNKNOWN_ERROR.name)
        }.sortedBy { errorCode ->
            errorCode.codeValue()
        }
        val errorCode = errorCodes.first()
        return ResponseEntity
            .status(errorCode.httpStatus)
            .body(
                errorBody(errorCode)
            )
    }

    private fun ErrorCode.codeValue(): Int {
        return this.code.split("-")[1].toInt()
    }

    private fun errorBody(errorCode: ErrorCode) = ErrorResponse.of(errorCode)
}