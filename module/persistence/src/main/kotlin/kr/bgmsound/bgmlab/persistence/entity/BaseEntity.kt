package kr.bgmsound.bgmlab.persistence.entity

import jakarta.persistence.Column
import jakarta.persistence.EntityListeners
import jakarta.persistence.MappedSuperclass
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(value = [AuditingEntityListener::class])
class BaseEntity {
    @CreatedDate
    @Column(name = "created_at", updatable = false, nullable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
}