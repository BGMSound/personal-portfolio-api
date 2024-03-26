package kr.bgmsound.bgmlab.authentication

interface AuthenticationStrategyProvider {

    fun getStrategy(type: LoginType): AuthenticationStrategy

}