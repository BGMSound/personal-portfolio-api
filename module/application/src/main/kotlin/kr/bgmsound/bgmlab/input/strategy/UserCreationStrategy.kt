package kr.bgmsound.bgmlab.input.strategy

import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.output.authentication.LoginProviderType

interface UserCreationStrategy {

    fun createNewUser(provider: LoginProviderType, id: String): User

}