package kr.bgmsound.bgmlab.api.auth.dto.response

data class RefreshResponse(
    val accessToken: String
) {
    companion object {
        fun from(accessToken: String): RefreshResponse {
            return RefreshResponse(accessToken = accessToken)
        }
    }
}