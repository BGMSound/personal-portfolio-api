package kr.bgmsound.bgmlab.strategy

import kr.bgmsound.bgmlab.model.User

interface UserCreationStrategy {

    fun createNewUser(provider: LoginProviderType, id: String): User

}