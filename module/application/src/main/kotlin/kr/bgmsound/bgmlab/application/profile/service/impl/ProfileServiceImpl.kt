package kr.bgmsound.bgmlab.application.profile.service.impl

import kr.bgmsound.bgmlab.application.profile.service.ProfileService
import kr.bgmsound.bgmlab.model.Profile
import kr.bgmsound.bgmlab.repository.SingletonProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    private val profileRepository: SingletonProfileRepository
) : ProfileService {

    override fun getProfile(): Profile {
        return profileRepository.getProfile()
    }

    override fun updateProfile(profile: Profile) {
        profileRepository.updateProfile(profile)
    }
}