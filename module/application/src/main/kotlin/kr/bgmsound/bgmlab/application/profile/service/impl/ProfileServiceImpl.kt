package kr.bgmsound.bgmlab.application.profile.service.impl

import kr.bgmsound.bgmlab.application.profile.dto.ProfilePatchDto
import kr.bgmsound.bgmlab.application.profile.service.ProfileService
import kr.bgmsound.bgmlab.model.Profile
import kr.bgmsound.bgmlab.application.profile.ProfileRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ProfileServiceImpl(
    private val profileRepository: ProfileRepository
) : ProfileService {

    @Transactional(readOnly = true)
    override fun getProfile(): Profile {
        return profileRepository.get()
    }

    @Transactional
    override fun updateProfile(profile: ProfilePatchDto) {
        profileRepository.update(profile)
    }
}