package kr.bgmsound.bgmlab.model

import java.time.LocalDateTime

data class User(
    val id: String,
    val displayId: String,
    val name: String,
    val roles: List<Role>,
    val createAt: LocalDateTime
) {
    sealed interface Account

    data class SocialAccount(
        val provider: String,
        val socialId: String
    ) : Account


    fun getRepresentativeRole(): Role {
        return roles.sorted().reversed().first()
    }
}