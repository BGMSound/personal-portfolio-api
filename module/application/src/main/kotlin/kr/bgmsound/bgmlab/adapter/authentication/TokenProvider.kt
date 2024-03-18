package kr.bgmsound.bgmlab.adapter.authentication

import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.model.Token
import kr.bgmsound.bgmlab.model.TokenType

interface TokenProvider {

    fun createToken(type: TokenType, id: String, authorities: List<Role>): Token

    fun extractIdFromToken(token: String): String

    fun extractRolesFromToken(token: String): List<Role>

}