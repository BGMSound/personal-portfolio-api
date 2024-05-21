package kr.bgmsound.bgmlab.persistence.entity.profile

import jakarta.persistence.*
import kr.bgmsound.bgmlab.persistence.entity.BaseAuditEntity

@Entity(name = "profile")
class SingletonProfileEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 1,

    @Column(nullable = false)
    val name: String,

    @Column(nullable = false)
    val profileImageUrl: String,

    @Column
    val description: String?,

    @Column
    val email: String?,

    @Column
    val location: String?,

    @Column
    val organization: String?,

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "link_tree", joinColumns = [JoinColumn(name = "profile_id")])
    val linkTree: List<String> = emptyList(),

    @Column
    val readMe: String?
) : BaseAuditEntity()