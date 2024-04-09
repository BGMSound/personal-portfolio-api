package kr.bgmsound.bgmlab.application.authentication

interface AuthenticationStrategyProvider {

    fun getStrategy(type: AuthenticationType): AuthenticationStrategy

}