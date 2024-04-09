package kr.bgmsound.bgmlab.persistence.entity.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.IdClass
import java.io.Serializable

@Entity
@IdClass(UserTokenEntityKey::class)
class UserTokenEntity(
    @Id
    @Column(name = "user_id")
    val userId: String,

    @Id
    @Column(name = "token")
    val token: String
)

data class UserTokenEntityKey(
    val userId: String = "",
    val token: String = ""
) : Serializable


