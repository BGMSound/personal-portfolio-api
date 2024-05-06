package kr.bgmsound.bgmlab.persistence.repository.impl

import jakarta.annotation.PostConstruct
import kr.bgmsound.bgmlab.application.profile.LinkTreeParser
import kr.bgmsound.bgmlab.application.profile.LocationParser
import kr.bgmsound.bgmlab.model.Profile
import kr.bgmsound.bgmlab.persistence.entity.profile.SingletonProfileEntity
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaProfileRepository
import kr.bgmsound.bgmlab.repository.SingletonProfileRepository
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class SingletonProfileRepositoryImpl(
    private val linkTreeParser: LinkTreeParser,
    private val locationParser: LocationParser,
    private val jpaProfileRepository: JpaProfileRepository,

    @Value("\${app.profile.default.name}") private val defaultName: String,
    @Value("\${app.profile.default.profile-image-url}") private val defaultProfileImageUrl: String
) : SingletonProfileRepository {

    @PostConstruct
    fun initialize() {
        if (jpaProfileRepository.count() != 0L) {
            return
        }
        jpaProfileRepository.save(defaultProfileEntity)
    }

    override fun getProfile(): Profile {
        return jpaProfileRepository.getProfile()
    }

    override fun updateProfile(profile: Profile) {
        TODO("Not yet implemented")
    }

    private val defaultProfileEntity get(): SingletonProfileEntity {
        return SingletonProfileEntity(
            name = defaultName,
            profileImageUrl = defaultProfileImageUrl,
            description = null,
            email = null,
            location = null,
            organization = null,
            linkTree = emptyList(),
            readMe = null
        )
    }

    private fun JpaProfileRepository.getProfile(): Profile {
        val profileEntity: SingletonProfileEntity = findAll().first()
        return Profile(
            name = profileEntity.name,
            profileImageUrl = profileEntity.profileImageUrl,
            email = profileEntity.email,
            description = profileEntity.description,
            location = locationParser.parseLocation(profileEntity.location),
            organization = profileEntity.organization,
            linkTree = linkTreeParser.parseLinkTree(profileEntity.linkTree),
            readMe = profileEntity.readMe
        )
    }
}