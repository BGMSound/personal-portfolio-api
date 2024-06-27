package kr.bgmsound.bgmlab.persistence.repository.jpa

import kr.bgmsound.bgmlab.persistence.entity.user.UserTokenEntity
import kr.bgmsound.bgmlab.persistence.entity.user.UserTokenEntityKey
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserTokenRepository : JpaRepository<UserTokenEntity, UserTokenEntityKey>