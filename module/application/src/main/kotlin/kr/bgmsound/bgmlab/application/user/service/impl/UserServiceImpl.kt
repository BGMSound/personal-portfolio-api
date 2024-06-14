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
    override fun changeDisplayId(id: String, newDisplayId: String) {
        val user = userRepository.findById(id) ?: throw UserNotFoundException()
        if (userRepository.findByDisplayId(newDisplayId) != null) {
            throw UserAlreadyExistsException()
        }
        userRepository.save(user.copy(displayId = newDisplayId))
    }

    @Transactional
    override fun changeName(id: String, newName: String) {
        val user = userRepository.findById(id) ?: throw UserNotFoundException()

        userRepository.save(user.copy(name = newName))
    }
}