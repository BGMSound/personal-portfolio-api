package kr.bgmsound.bgmlab.model

import java.net.URL

class Profile(
    name: String,
    profileImageUrl: String,
    val description: String?,
    val email: String?,
    val location: Location?,
    val organization: String?,
    val linkTree: List<URL>,
    val readMe: String?
) {
    private val _name: String = name.trim()
    private val _profileImageUrl: String = profileImageUrl.trim()

    val name: String? get() = _name.ifBlank { null }
    val profileImageUrl: String? get() = _profileImageUrl.ifBlank { null }

    data class Location(
        val country: String,
        val city: String
    ) {
        override fun toString(): String {
            return "$city, $country"
        }
    }
}