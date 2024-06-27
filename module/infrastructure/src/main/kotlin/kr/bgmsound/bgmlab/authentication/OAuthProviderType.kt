package kr.bgmsound.bgmlab.authentication

enum class OAuthProviderType {
    GOOGLE,
    GITHUB,
    KAKAO;

    companion object {
        fun from(provider: String): OAuthProviderType {
            return OAuthProviderType.valueOf(provider.uppercase())
        }
    }
}