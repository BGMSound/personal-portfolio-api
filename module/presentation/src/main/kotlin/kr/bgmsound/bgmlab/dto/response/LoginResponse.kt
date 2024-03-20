package kr.bgmsound.bgmlab.dto.response

import kr.bgmsound.bgmlab.authentication.dto.LoggedInUserDto
import kr.bgmsound.bgmlab.model.Role

data class LoginResponse(
    val role: Role,
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun from(loggedInUser: LoggedInUserDto): LoginResponse {
            return LoginResponse(
                role = loggedInUser.getRepresentativeRole(),
                accessToken = loggedInUser.accessToken,
                refreshToken = loggedInUser.refreshToken
            )
        }
    }
}