package kr.bgmsound.bgmlab.application.user.service.impl

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

    @Transactional(readOnly = true)
    override fun findById(id: String): User {
        return userRepository.findById(id) ?: throw UserNotFoundException()
    }

    @Transactional(readOnly = true)
    override fun findByDisplayId(displayId: String): User {
        return userRepository.findByDisplayId(displayId) ?: throw UserNotFoundException()
    }

    @Transactional
    override fun changeDisplayId(userId: String, displayId: String) {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException()
        if (userRepository.findByDisplayId(displayId) != null) {
            throw UserAlreadyExistsException()
        }
        userRepository.save(user.copy(displayId = displayId))
    }

    @Transactional
    override fun changeName(userId: String, name: String) {
        val user = userRepository.findById(userId) ?: throw UserNotFoundException()

        userRepository.save(user.copy(name = name))
    }
}