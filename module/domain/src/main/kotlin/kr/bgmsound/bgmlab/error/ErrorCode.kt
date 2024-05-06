package kr.bgmsound.bgmlab.error

enum class ErrorCode(
    val code: String,
    val message: String
) {
    UNKNOWN_ERROR("unknown-000", "알 수 없는 오류가 발생했습니다."),

    NOT_SIGNUP("common-001", "가입 후 이용해주세요."),
    NOT_AUTHORIZED("common-002", "권한이 없습니다."),
    AUTHENTICATION_FAIL("common-003", "인증에 실패했습니다."),
    TOKEN_EXPIRED("common-004", "토큰이 만료되었습니다."),
    NOT_FOUND("common-005", "해당 주소를 찾을 수 없습니다."),

    INVALID_ERROR("invalid-001", "유효하지 않은 요청입니다."),
    INVALID_LOCATION_FORMAT("invalid-002", "유효하지 않은 위치 형식입니다."),

    USER_NOT_FOUND("user-001", "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS("user-002", "이미 존재하는 사용자입니다.");

    companion object {
        private val map = entries.associateBy { it.code }

        fun getByCode(code: String): ErrorCode = map[code] ?: UNKNOWN_ERROR
    }
}