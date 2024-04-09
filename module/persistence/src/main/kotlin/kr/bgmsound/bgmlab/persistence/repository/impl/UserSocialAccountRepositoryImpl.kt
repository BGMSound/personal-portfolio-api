package kr.bgmsound.bgmlab.persistence.repository.impl

import kr.bgmsound.bgmlab.OAuthProviderType
import kr.bgmsound.bgmlab.application.authentication.SocialAccount
import kr.bgmsound.bgmlab.application.authentication.UserSocialAccountRepository
import kr.bgmsound.bgmlab.persistence.entity.user.UserSocialAccountEntity
import kr.bgmsound.bgmlab.persistence.entity.user.UserSocialAccountEntityKey
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaUserSocialAccountRepository
import org.springframework.stereotype.Repository
import kotlin.jvm.optionals.getOrNull

@Repository
class UserSocialAccountRepositoryImpl(
    private val jpaUserSocialAccountRepository: JpaUserSocialAccountRepository
) : UserSocialAccountRepository {

    override fun findBySocialId(provider: String, socialId: String): SocialAccount? {
        val key = UserSocialAccountEntityKey(provider = OAuthProviderType.from(provider), socialId = socialId)
        return jpaUserSocialAccountRepository.findById(key).getOrNull()?.toDomain()
    }

    override fun save(account: SocialAccount) {
        jpaUserSocialAccountRepository.save(account.toEntity())
    }

    private fun SocialAccount.toEntity(): UserSocialAccountEntity {
        return UserSocialAccountEntity(
            provider = OAuthProviderType.from(provider),
            socialId = socialId,
            userId = userId
        )
    }
}