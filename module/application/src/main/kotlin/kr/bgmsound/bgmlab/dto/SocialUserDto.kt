package kr.bgmsound.bgmlab.dto

import kr.bgmsound.bgmlab.model.Role

data class SocialUserDto(
    val id: String,
    val name: String,
    val roles: List<Role>,
    val isNewUser: Boolean
) {
    fun getRepresentativeRole(): Role {
        return roles.maxByOrNull { it.priority } ?: Role.NEED_SIGNUP
    }
}