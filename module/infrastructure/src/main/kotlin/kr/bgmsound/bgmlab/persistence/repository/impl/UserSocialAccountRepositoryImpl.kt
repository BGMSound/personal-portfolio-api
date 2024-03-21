package kr.bgmsound.bgmlab.persistence.repository.impl

import kr.bgmsound.bgmlab.authentication.UserSocialAccountRepository
import kr.bgmsound.bgmlab.authentication.dto.SocialAccount
import kr.bgmsound.bgmlab.persistence.entity.user.UserSocialAccountEntity
import kr.bgmsound.bgmlab.persistence.entity.user.UserSocialAccountEntityKey
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaUserSocialAccountRepository
import kr.bgmsound.bgmlab.authentication.LoginType
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class UserSocialAccountRepositoryImpl(
    private val jpaUserSocialAccountRepository: JpaUserSocialAccountRepository,
) : UserSocialAccountRepository {

    override fun findBySocialId(provider: LoginType, socialId: String): SocialAccount? {
        val key = UserSocialAccountEntityKey(provider = provider, socialId = socialId)
        return jpaUserSocialAccountRepository.findById(key).getOrNull()?.toDomain()
    }

    override fun save(account: SocialAccount) {
        jpaUserSocialAccountRepository.save(account.toEntity())
    }

    private fun SocialAccount.toEntity(): UserSocialAccountEntity {
        return UserSocialAccountEntity(
            provider = provider,
            socialId = socialId,
            userId = userId
        )
    }
}