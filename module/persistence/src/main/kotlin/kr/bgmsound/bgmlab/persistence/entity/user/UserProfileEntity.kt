package kr.bgmsound.bgmlab.persistence.entity.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import kr.bgmsound.bgmlab.model.User

@Entity(name = "user_profile")
class UserProfileEntity(
    @Id
    @Column(name = "user_id")
    val userId: String,

    @Column(name = "profile_url")
    val profileUrl: String,

    @Column(name = "description")
    val description: String,

    @Column(name = "read_me")
    val readMe: String
) {

    fun toDomain(): User.Profile {
        return User.Profile(
            profileUrl = profileUrl,
            description = description,
            readMe = readMe
        )
    }
}