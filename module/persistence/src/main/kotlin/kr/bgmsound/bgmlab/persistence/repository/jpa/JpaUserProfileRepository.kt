package kr.bgmsound.bgmlab.persistence.repository.jpa

import kr.bgmsound.bgmlab.persistence.entity.user.UserProfileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaUserProfileRepository : JpaRepository<UserProfileEntity, String>