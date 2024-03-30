package kr.bgmsound.bgmlab.persistence.entity.user

import jakarta.persistence.*
import kr.bgmsound.bgmlab.authentication.OAuthProviderType
import kr.bgmsound.bgmlab.authentication.SocialAccount
import kr.bgmsound.bgmlab.persistence.entity.BaseEntity
import java.io.Serializable

@IdClass(UserSocialAccountEntityKey::class)
@Entity(name = "user_social_account")
class UserSocialAccountEntity(
    @Id
    @Column(name = "provider", updatable = false)
    @Enumerated(EnumType.STRING)
    val provider: OAuthProviderType,

    @Id
    @Column(name = "social_id", updatable = false)
    val socialId: String,

    @Column(name = "user_id", updatable = false)
    val userId: String
) : BaseEntity() {

    fun toDomain(): SocialAccount {
        return SocialAccount(
            userId = userId,
            provider = provider.toString(),
            socialId = socialId
        )
    }
}

data class UserSocialAccountEntityKey(
    val provider: OAuthProviderType = OAuthProviderType.GOOGLE,
    val socialId: String = "",
) : Serializable