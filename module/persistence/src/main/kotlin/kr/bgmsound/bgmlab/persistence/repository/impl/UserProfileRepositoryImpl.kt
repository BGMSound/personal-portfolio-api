package kr.bgmsound.bgmlab.persistence.repository.impl

import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.persistence.entity.user.UserProfileEntity
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaUserProfileRepository
import kr.bgmsound.bgmlab.repository.UserProfileRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Repository

@Repository
class UserProfileRepositoryImpl(
    private val jpaUserProfileRepository: JpaUserProfileRepository
) : UserProfileRepository {

    override fun save(userId: String, userProfile: User.Profile) {
        jpaUserProfileRepository.save(userProfile.toEntity(userId))
    }

    override fun findByUserId(userId: String): User.Profile? {
        return jpaUserProfileRepository.findByIdOrNull(userId)?.toDomain()
    }

    private fun User.Profile.toEntity(userId: String): UserProfileEntity {
        return UserProfileEntity(
            userId = userId,
            profileUrl = profileUrl,
            description = description,
            readMe = readMe
        )
    }
}