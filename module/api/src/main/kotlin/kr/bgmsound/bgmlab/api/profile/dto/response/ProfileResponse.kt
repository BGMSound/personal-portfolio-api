package kr.bgmsound.bgmlab.api.profile.dto.response

import kr.bgmsound.bgmlab.model.Profile
import kr.bgmsound.bgmlab.model.Profile.Location
import java.net.URL

data class ProfileResponse(
    val name: String,
    val profileImageUrl: String,
    val description: String?,
    val email: String?,
    val location: Location?,
    val organization: String?,
    val linkTree: List<URL>,
    val readMe: String?
) {
    companion object {
        fun of(profile: Profile): ProfileResponse {
            if (profile.name == null || profile.profileImageUrl == null) {
                throw IllegalArgumentException("Profile name and profile image url cannot be empty or null on ProfileResponse")
            }
            return ProfileResponse(
                name = profile.name!!,
                profileImageUrl = profile.profileImageUrl!!,
                description = profile.description,
                email = profile.email,
                location = profile.location,
                organization = profile.organization,
                linkTree = profile.linkTree,
                readMe = profile.readMe
            )
        }
    }
}