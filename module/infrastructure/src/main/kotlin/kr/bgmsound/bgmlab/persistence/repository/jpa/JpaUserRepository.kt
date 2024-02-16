package kr.bgmsound.bgmlab.persistence.repository.jpa

import kr.bgmsound.bgmlab.persistence.entity.user.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserRepository : JpaRepository<UserEntity, String>