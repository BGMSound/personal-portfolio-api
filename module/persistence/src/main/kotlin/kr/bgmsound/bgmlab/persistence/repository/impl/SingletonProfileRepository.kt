package kr.bgmsound.bgmlab.persistence.repository.impl

import jakarta.annotation.PostConstruct
import kr.bgmsound.bgmlab.application.profile.LinkTreeParser
import kr.bgmsound.bgmlab.application.profile.LocationParser
import kr.bgmsound.bgmlab.model.Profile
import kr.bgmsound.bgmlab.persistence.entity.profile.SingletonProfileEntity
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaSingletonProfileRepository
import kr.bgmsound.bgmlab.application.profile.ProfileRepository
import kr.bgmsound.bgmlab.application.profile.dto.ProfilePatchDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class SingletonProfileRepository(
    @Value("\${app.profile.default.name}") private val defaultName: String,
    @Value("\${app.profile.default.profile-image-url}") private val defaultProfileImageUrl: String,

    private val jpaProfileRepository: JpaSingletonProfileRepository
) : ProfileRepository {

    @PostConstruct
    fun initialize() {
        if (jpaProfileRepository.count() == 0L) {
            jpaProfileRepository.save(defaultProfileEntity)
        }
    }

    override fun get(): Profile {
        return profileEntity.toProfile()
    }

    override fun update(profile: ProfilePatchDto) {
        val originProfileEntity = profileEntity
        val updatedProfileEntity = originProfileEntity.apply(profile)
        jpaProfileRepository.save(updatedProfileEntity)
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

    private val profileEntity get(): SingletonProfileEntity {
        return jpaProfileRepository.findById(1L).orElse(defaultProfileEntity)
    }

    private fun SingletonProfileEntity.toProfile(): Profile {
        return Profile(
            name = name,
            profileImageUrl = profileImageUrl,
            description = description,
            email = email,
            location = parseLocation(location),
            organization = organization,
            linkTree = LinkTreeParser.parseLinkTree(linkTree),
            readMe = readMe,
            updatedAt = updatedAt
        )
    }

    private fun SingletonProfileEntity.apply(patch: ProfilePatchDto): SingletonProfileEntity {
        return SingletonProfileEntity(
            id = id,
            name = patch.name ?: name,
            profileImageUrl = patch.profileImageUrl ?: profileImageUrl,
            description = patch.description,
            email = patch.email,
            location = patch.location?.toString() ?: location,
            organization = patch.organization,
            linkTree = patch.linkTree?.map { it.toString() } ?: linkTree,
            readMe = patch.readMe
        )
    }

    private fun parseLocation(locationFormat: String?): Profile.Location? {
        return locationFormat?.let { LocationParser.parseLocation(it) }
    }
}