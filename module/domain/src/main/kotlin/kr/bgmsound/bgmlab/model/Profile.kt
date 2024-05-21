package kr.bgmsound.bgmlab.model

import java.net.URL
import java.time.LocalDateTime

data class Profile(
    val name: String,
    val profileImageUrl: String,
    val description: String?,
    val email: String?,
    val location: Location?,
    val organization: String?,
    val linkTree: List<URL>,
    val readMe: String?,
    val updatedAt: LocalDateTime
) {

    data class Location(
        val country: String,
        val city: String
    ) {
        override fun toString(): String {
            return "$city, $country"
        }
    }
}