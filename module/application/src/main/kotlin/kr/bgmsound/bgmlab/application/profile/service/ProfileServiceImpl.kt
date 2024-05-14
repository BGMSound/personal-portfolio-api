package kr.bgmsound.bgmlab.application.profile.service

import kr.bgmsound.bgmlab.service.ProfileService
import kr.bgmsound.bgmlab.model.Profile
import kr.bgmsound.bgmlab.repository.SingletonProfileRepository
import org.springframework.stereotype.Service

@Service
class ProfileServiceImpl(
    private val profileRepository: SingletonProfileRepository
) : ProfileService {

    override fun getProfile(): Profile {
        return profileRepository.get()
    }

    override fun updateProfile(profile: Profile) {
        profileRepository.update(profile)
    }
}