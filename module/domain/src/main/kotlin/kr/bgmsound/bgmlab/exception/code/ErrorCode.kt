package kr.bgmsound.bgmlab.exception.code

enum class ErrorCode(
    val code: String,
    val message: String
) {
    UNKNOWN_ERROR("unknown-000", "알 수 없는 오류가 발생했습니다."),

    ;
}