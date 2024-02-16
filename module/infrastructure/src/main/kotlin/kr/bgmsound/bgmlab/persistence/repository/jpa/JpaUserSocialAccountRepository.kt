package kr.bgmsound.bgmlab.persistence.repository.jpa

import kr.bgmsound.bgmlab.persistence.entity.user.UserSocialAccountEntity
import kr.bgmsound.bgmlab.persistence.entity.user.UserSocialAccountEntityKey
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserSocialAccountRepository : JpaRepository<UserSocialAccountEntity, UserSocialAccountEntityKey>