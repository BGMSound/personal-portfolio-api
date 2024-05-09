package kr.bgmsound.bgmlab.application.authentication

interface AuthenticationSupportProvider {

    fun from(type: AuthenticationType): AuthenticationSupport

}