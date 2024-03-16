package kr.bgmsound.bgmlab.dto.account

import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.strategy.LoginProviderType

data class SocialAccount(
    override val userId: String,
    val provider: LoginProviderType,
    val socialId: String
) : User.Account {

    companion object {
        fun of(user: User, provider: LoginProviderType, socialId: String): SocialAccount {
            return SocialAccount(
                userId = user.id,
                provider = provider,
                socialId = socialId
            )
        }
    }
}