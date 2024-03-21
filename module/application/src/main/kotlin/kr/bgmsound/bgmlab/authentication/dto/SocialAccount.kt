package kr.bgmsound.bgmlab.authentication.dto

import kr.bgmsound.bgmlab.authentication.LoginType
import kr.bgmsound.bgmlab.model.User

data class SocialAccount(
    override val userId: String,
    val provider: LoginType,
    val socialId: String
) : User.Account {

    companion object {
        fun of(user: User, provider: LoginType, socialId: String): SocialAccount {
            return SocialAccount(
                userId = user.id,
                provider = provider,
                socialId = socialId
            )
        }
    }
}