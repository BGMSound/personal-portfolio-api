package kr.bgmsound.bgmlab.user.service.impl

import kr.bgmsound.bgmlab.error.exception.UserAlreadyExistsException
import kr.bgmsound.bgmlab.error.exception.UserNotFoundException
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.repository.UserRepository
import kr.bgmsound.bgmlab.service.UserService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
) : UserService {

    @Transactional
    override fun changeDisplayId(userId: String, displayId: String) {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException()
        if(userRepository.findByDisplayId(displayId) != null) {
            throw UserAlreadyExistsException()
        }
        userRepository.save(user.copy(displayId = displayId))
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