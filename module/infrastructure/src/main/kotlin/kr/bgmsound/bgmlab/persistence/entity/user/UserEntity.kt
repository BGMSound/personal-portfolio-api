package kr.bgmsound.bgmlab.persistence.entity.user

import jakarta.persistence.*
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.persistence.entity.BaseAuditEntity

@Entity(name = "users")
class UserEntity(
    @Id
    @Column(name = "id")
    val id: String,

    @Column(name = "display_id", unique = true, nullable = false)
    val displayId: String,

    @Column(name = "name", nullable = false)
    val name: String,

    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = [JoinColumn(name = "user_id")])
    val roles: List<Role>
) : BaseAuditEntity() {

    fun toDomain(): User {
        return User(
            id = id,
            displayId = displayId,
            name = name,
            roles = roles,
            createAt = createdAt
        )
    }
}