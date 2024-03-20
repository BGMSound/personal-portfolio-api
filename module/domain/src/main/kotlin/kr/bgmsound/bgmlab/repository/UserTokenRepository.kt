package kr.bgmsound.bgmlab.repository

import kr.bgmsound.bgmlab.model.Token

interface UserTokenRepository {

    fun exists(userId: String, token: Token): Boolean

    fun notExists(userId: String, token: Token): Boolean {
        return !exists(userId, token)
    }

    fun save(userId: String, token: Token)

}
