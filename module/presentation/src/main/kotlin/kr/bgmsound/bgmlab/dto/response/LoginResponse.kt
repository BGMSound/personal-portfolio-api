package kr.bgmsound.bgmlab.dto.response

import kr.bgmsound.bgmlab.model.Role

data class LoginResponse(
    val role: Role,
    val accessToken: String,
    val refreshToken: String
)