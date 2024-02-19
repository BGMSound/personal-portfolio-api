package kr.bgmsound.bgmlab.persistence.entity.user

import jakarta.persistence.*
import kr.bgmsound.bgmlab.output.provider.LoginProviderType
import kr.bgmsound.bgmlab.persistence.entity.BaseEntity
import java.io.Serializable

@IdClass(UserSocialAccountEntityKey::class)
@Entity(name = "user_social_account")
class UserSocialAccountEntity(
    @Id
    @Column(name = "provider", updatable = false)
    @Enumerated(EnumType.STRING)
    val provider: LoginProviderType,

    @Id
    @Column(name = "social_id", updatable = false)
    val socialId: String,

    @Column(name = "user_id", updatable = false)
    val userId: String,
) : BaseEntity()

data class UserSocialAccountEntityKey(
    val provider: LoginProviderType = LoginProviderType.GOOGLE,
    val socialId: String = "",
) : Serializable