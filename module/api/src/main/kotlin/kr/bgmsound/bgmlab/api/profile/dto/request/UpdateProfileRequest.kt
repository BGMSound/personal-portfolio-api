package kr.bgmsound.bgmlab.api.profile.dto.request

data class UpdateProfileRequest(
    val name: String?,
    val profileImageUrl: String?,
    val description: String?,
    val email: String?,
    val location: String?,
    val organization: String?,
    val linkTree: List<String>,
    val readMe: String?
)