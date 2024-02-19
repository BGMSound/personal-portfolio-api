package kr.bgmsound.bgmlab.advice.security

import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

data class APIAuthentication(
    private val userId: String,
    private val accessToken: String,
    private val roles: MutableList<GrantedAuthority>
) : Authentication {

    private var isAuthenticated = true

    override fun getName(): String = userId

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return roles
    }

    override fun getPrincipal(): String = userId

    override fun getCredentials(): String = accessToken

    override fun getDetails() = null

    override fun isAuthenticated(): Boolean = isAuthenticated

    override fun setAuthenticated(isAuthenticated: Boolean) {
        this.isAuthenticated = isAuthenticated
    }
}