package kr.bgmsound.bgmlab.adapter.authentication

import kr.bgmsound.bgmlab.model.Role

interface TokenProvider {

    fun createAccessToken(id: String, authorities: List<Role>): String

    fun createRefreshToken(id: String, authorities: List<Role>): String

    fun extractIdFromToken(token: String): String

    fun extractRolesFromToken(token: String): List<Role>

}