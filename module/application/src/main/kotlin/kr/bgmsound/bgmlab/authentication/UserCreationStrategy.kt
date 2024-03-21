package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.LoginType
import kr.bgmsound.bgmlab.model.User

interface UserCreationStrategy {

    fun createNewUser(provider: LoginType, id: String): User

}