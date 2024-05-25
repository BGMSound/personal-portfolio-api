package kr.bgmsound.bgmlab.application.profile.service.impl

import kr.bgmsound.bgmlab.application.profile.dto.ProfilePatchDto
import kr.bgmsound.bgmlab.application.profile.service.ProfileService
import kr.bgmsound.bgmlab.model.Profile
import kr.bgmsound.bgmlab.application.profile.ProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    private val profileRepository: ProfileRepository
) : ProfileService {

    override fun getProfile(): Profile {
        return profileRepository.get()
    }

    override fun updateProfile(profile: ProfilePatchDto) {
        profileRepository.update(profile)
    }
}