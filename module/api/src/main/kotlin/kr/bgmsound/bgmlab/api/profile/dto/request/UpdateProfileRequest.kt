package kr.bgmsound.bgmlab.api.profile.dto.request

data class UpdateProfileRequest(
    val name: String? = null,
    val profileImageUrl: String? = null,
    val description: String? = null,
    val email: String? = null,
    val location: String? = null,
    val organization: String? = null,
    val linkTree: List<String>? = null,
    val readMe: String? = null
)