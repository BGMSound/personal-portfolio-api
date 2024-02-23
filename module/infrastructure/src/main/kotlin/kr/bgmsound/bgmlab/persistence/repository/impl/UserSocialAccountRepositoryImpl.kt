package kr.bgmsound.bgmlab.persistence.repository.impl

import kr.bgmsound.bgmlab.exception.InvalidException
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.output.authentication.LoginProviderType
import kr.bgmsound.bgmlab.persistence.entity.user.UserSocialAccountEntity
import kr.bgmsound.bgmlab.persistence.entity.user.UserSocialAccountEntityKey
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaUserRepository
import kr.bgmsound.bgmlab.persistence.repository.jpa.JpaUserSocialAccountRepository
import kr.bgmsound.bgmlab.repository.UserSocialAccountRepository
import org.springframework.stereotype.Repository

@Repository
class UserSocialAccountRepositoryImpl(
    private val jpaUserSocialAccountRepository: JpaUserSocialAccountRepository,
    private val jpaUserRepository: JpaUserRepository
) : UserSocialAccountRepository {

    override fun findBySocialId(provider: String, socialId: String): User? {
        val userId = jpaUserSocialAccountRepository
            .findById(
                UserSocialAccountEntityKey(
                    provider = getProvider(providerName = provider),
                    socialId = socialId
                )
            )
            .orElse(null)?.userId ?: return null
        return jpaUserRepository.findById(userId).orElse(null).toDomain()
    }

    override fun save(userId: String, account: User.Account) {
        if (account !is User.SocialAccount) {
            throw IllegalArgumentException("account is not SocialAccount type.")
        }
        jpaUserSocialAccountRepository.save(
            UserSocialAccountEntity(
                provider = getProvider(account.provider),
                socialId = account.socialId,
                userId = userId
            )
        )
    }


    private fun getProvider(providerName: String): LoginProviderType {
        return try {
            LoginProviderType.valueOf(providerName)
        } catch (e: IllegalArgumentException) {
            throw InvalidException()
        }
    }
}