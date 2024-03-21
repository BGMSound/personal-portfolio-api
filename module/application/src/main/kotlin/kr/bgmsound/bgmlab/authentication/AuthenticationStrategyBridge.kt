package kr.bgmsound.bgmlab.authentication

interface AuthenticationStrategyBridge {

    fun getProvider(type: LoginType): AuthenticationStrategy

}