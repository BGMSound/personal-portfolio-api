package kr.bgmsound.bgmlab.user.service.impl

import kr.bgmsound.bgmlab.error.exception.UserNotFoundException
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserRepository
import kr.bgmsound.bgmlab.service.UserService
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    override fun changeDisplayId(userId: String, displayId: String) {
        TODO("Not yet implemented")
    }

    override fun changeName(userId: String, name: String) {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException()
        userRepository.save(user.copy(name = name))
    }

    override fun getProfile(userId: String): User.Profile {
        TODO("Not yet implemented")
    }

    override fun updateProfile(userId: String, profile: User.Profile) {
        TODO("Not yet implemented")
    }
}