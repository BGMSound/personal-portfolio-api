package kr.bgmsound.bgmlab.advice.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

data class APIAuthentication(
    private val userId: String,
    private val accessToken: String,
    private val roles: List<GrantedAuthority>
) : Authentication {

    private var isAuthenticated = true

    override fun getName(): String = userId

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return roles
    }

    override fun getPrincipal(): String = userId

    override fun getCredentials(): String = accessToken

    override fun getDetails() = null

    override fun isAuthenticated(): Boolean = isAuthenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }

    companion object {
        fun of(userId: String, accessToken: String, roles: List<GrantedAuthority>): APIAuthentication {
            return APIAuthentication(userId, accessToken, roles.toMutableList())
        }
    }
}