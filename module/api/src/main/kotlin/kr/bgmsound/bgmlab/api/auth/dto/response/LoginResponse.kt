package kr.bgmsound.bgmlab.api.auth.dto.response

import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticatedUserDto
import kr.bgmsound.bgmlab.model.Role

data class LoginResponse(
    val role: Role,
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun from(loggedInUser: AuthenticatedUserDto): LoginResponse {
            return LoginResponse(
                role = loggedInUser.getRepresentativeRole(),
                accessToken = loggedInUser.accessToken.provider,
                refreshToken = loggedInUser.refreshToken.provider
            )
        }
    }
}