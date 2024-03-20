package kr.bgmsound.bgmlab

import kr.bgmsound.bgmlab.authentication.dto.LoginType
import kr.bgmsound.bgmlab.model.User

interface UserCreationStrategy {

    fun createNewUser(provider: LoginType, id: String): User

}