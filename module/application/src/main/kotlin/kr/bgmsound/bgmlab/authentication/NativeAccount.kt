package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.model.User

data class NativeAccount(
    override val userId: String,
    val password: String
) : User.Account