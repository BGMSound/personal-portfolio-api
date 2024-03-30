package kr.bgmsound.bgmlab.authentication.dto

import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.model.User

data class AuthenticatedUserDto(
    val id: String,
    val name: String,
    val roles: List<Role>,
    val accessToken: Token,
    val refreshToken: Token
) {

    fun getRepresentativeRole(): Role {
        return roles.maxByOrNull { it.priority } ?: Role.NEED_SIGNUP
    }

    companion object {
        fun of(user: User, accessToken: Token, refreshToken: Token): AuthenticatedUserDto {
            return AuthenticatedUserDto(
                id = user.id,
                name = user.name,
                roles = user.roles,
                accessToken = accessToken,
                refreshToken = refreshToken
            )
        }
    }
}