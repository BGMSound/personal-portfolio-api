package kr.bgmsound.bgmlab.persistence.repository.impl

import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.persistence.entity.user.UserEntity
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaUserRepository
import kr.bgmsound.bgmlab.repository.UserRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepositoryImpl(
    private val jpaUserRepository: JpaUserRepository,
) : UserRepository {

    override fun findById(id: String): User? {
        return jpaUserRepository.findById(id).orElse(null).toDomain()
    }

    override fun save(user: User) {
        jpaUserRepository.save(user.toEntity())
    }

    private fun User.toEntity(): UserEntity {
        return UserEntity(
            id = this.id,
            displayId = this.displayId,
            name = this.name,
            roles = this.roles
        )
    }
}