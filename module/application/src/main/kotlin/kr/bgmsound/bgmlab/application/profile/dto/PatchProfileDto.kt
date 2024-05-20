package kr.bgmsound.bgmlab.application.profile.dto

import kr.bgmsound.bgmlab.model.Profile
import java.net.URL

data class PatchProfileDto(
    val name: String? = null,
    val profileImageUrl: String? = null,
    val description: String? = null,
    val email: String? = null,
    val location: Profile.Location? = null,
    val organization: String? = null,
    val linkTree: List<URL>? = null,
    val readMe: String? = null
)