package kr.bgmsound.bgmlab.persistence.repository.impl

import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.persistence.entity.user.UserTokenEntity
import kr.bgmsound.bgmlab.persistence.entity.user.UserTokenEntityKey
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaUserTokenRepository
import kr.bgmsound.bgmlab.repository.UserTokenRepository
import org.springframework.stereotype.Repository

@Repository
class UserTokenRepositoryImpl(
    private val jpaUserTokenRepository: JpaUserTokenRepository
) : UserTokenRepository {

    override fun exists(userId: String, token: Token): Boolean {
        return jpaUserTokenRepository.existsById(UserTokenEntityKey(userId = userId, token = token.provider))
    }

    override fun save(userId: String, token: Token) {
        jpaUserTokenRepository.save(UserTokenEntity(userId = userId, token = token.provider))
    }
}