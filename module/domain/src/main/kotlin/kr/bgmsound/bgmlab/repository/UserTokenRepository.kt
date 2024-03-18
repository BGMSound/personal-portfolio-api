package kr.bgmsound.bgmlab.repository

import kr.bgmsound.bgmlab.model.Token

interface UserTokenRepository {

    fun save(userId: String, token: Token)

}
