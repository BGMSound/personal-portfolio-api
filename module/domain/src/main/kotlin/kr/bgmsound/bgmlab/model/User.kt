package kr.bgmsound.bgmlab.model

import java.time.LocalDateTime

data class User(
    val id: String,
    val displayId: String,
    val name: String,
    val roles: List<Role>,
    val createAt: LocalDateTime
) {
    interface Account {
        val userId: String
    }

    fun getRepresentativeRole(): Role {
        return roles.maxByOrNull { it.priority } ?: Role.NEED_SIGNUP
    }
}