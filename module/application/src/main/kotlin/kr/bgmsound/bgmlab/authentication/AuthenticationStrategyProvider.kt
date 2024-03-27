package kr.bgmsound.bgmlab.authentication

interface AuthenticationStrategyProvider {

    fun getStrategy(type: AuthenticationType): AuthenticationStrategy

}