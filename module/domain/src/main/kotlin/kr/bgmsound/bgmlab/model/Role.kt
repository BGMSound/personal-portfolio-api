package kr.bgmsound.bgmlab.model

enum class Role(
    val priority: Int,
) {
    NEED_SIGNUP(1),
    USER(2)
}