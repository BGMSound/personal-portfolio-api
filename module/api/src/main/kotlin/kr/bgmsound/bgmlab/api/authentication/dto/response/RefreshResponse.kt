package kr.bgmsound.bgmlab.api.authentication.dto.response

data class RefreshResponse(
    val accessToken: String
) {
    companion object {
        fun from(accessToken: String): RefreshResponse {
            return RefreshResponse(accessToken = accessToken)
        }
    }
}