package kr.bgmsound.bgmlab.persistence.repository.jpa

import kr.bgmsound.bgmlab.persistence.entity.profile.SingletonProfileEntity
import org.springframework.data.jpa.repository.JpaRepository

interface JpaProfileRepository : JpaRepository<SingletonProfileEntity, Long>