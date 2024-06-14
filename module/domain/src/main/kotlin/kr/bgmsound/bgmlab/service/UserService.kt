package kr.bgmsound.bgmlab.service

import kr.bgmsound.bgmlab.model.User

interface UserService {

    fun findById(id: String): User

    fun findByDisplayId(displayId: String): User

    fun changeDisplayId(id: String, newDisplayId: String)

    fun changeName(id: String, newName: String)

}