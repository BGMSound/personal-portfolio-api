package kr.bgmsound.bgmlab.authentication

interface AuthenticationStrategyManager {

    fun getStrategy(type: LoginType): AuthenticationStrategy

}