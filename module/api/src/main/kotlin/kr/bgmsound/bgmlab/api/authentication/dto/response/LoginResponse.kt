package kr.bgmsound.bgmlab.api.authentication.dto.response

import kr.bgmsound.bgmlab.application.authentication.dto.AuthenticatedUserDto
import kr.bgmsound.bgmlab.model.Role

data class LoginResponse(
    val displayId: String,
    val name: String,
    val role: Role,
    val accessToken: String,
    val refreshToken: String
) {
    companion object {
        fun from(loggedInUser: AuthenticatedUserDto): LoginResponse {
            return LoginResponse(
                displayId = loggedInUser.displayId,
                name = loggedInUser.name,
                role = loggedInUser.getRepresentativeRole(),
                accessToken = loggedInUser.accessToken.provider,
                refreshToken = loggedInUser.refreshToken.provider
            )
        }
    }
}