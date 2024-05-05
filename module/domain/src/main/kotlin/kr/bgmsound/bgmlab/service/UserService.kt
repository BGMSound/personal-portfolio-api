package kr.bgmsound.bgmlab.service

import kr.bgmsound.bgmlab.model.User

interface UserService {

    fun findById(id: String): User

    fun findByDisplayId(displayId: String): User

    fun changeDisplayId(userId: String, displayId: String)

    fun changeName(userId: String, name: String)

}