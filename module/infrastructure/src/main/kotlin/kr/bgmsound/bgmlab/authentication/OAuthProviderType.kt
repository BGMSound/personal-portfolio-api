package kr.bgmsound.bgmlab.authentication

enum class OAuthProviderType {
    GOOGLE,
    GITHUB,
    KAKAO
    ;

    companion object {
        fun from(provider: String): OAuthProviderType {
            return runCatching {
                OAuthProviderType.valueOf(provider.uppercase())
            }.getOrElse {
                throw IllegalArgumentException("Wrong OAuth Provider: $provider")
            }
        }

    }
}