package kr.bgmsound.bgmlab.advice

import kr.bgmsound.bgmlab.error.APIException
import kr.bgmsound.bgmlab.error.ErrorCode
import kr.bgmsound.bgmlab.shared.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class APIExceptionHandler {

    @ExceptionHandler(value = [APIException::class])
    fun handleApiException(exception: APIException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(exception.errorCode.httpStatus())
            .body(errorBodyOf(exception.errorCode))
    }

    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun handleInvalidException(exception: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val errorCodes = exception.bindingResult.fieldErrors.mapNotNull {
            it.defaultMessage?.let { code -> ErrorCode.getByCode(code) }
        }.sortedBy { errorCode ->
            errorCode.codeValue()
        }
        val errorCode = errorCodes.first()
        return ResponseEntity
            .status(errorCode.httpStatus())
            .body(errorBodyOf(errorCode))
    }

    private fun ErrorCode.codeValue(): Int {
        return this.code.split("-").last().toInt()
    }

    private fun errorBodyOf(errorCode: ErrorCode) = ErrorResponse.of(errorCode)
}