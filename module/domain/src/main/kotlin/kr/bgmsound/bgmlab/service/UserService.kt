package kr.bgmsound.bgmlab.service

import kr.bgmsound.bgmlab.model.User

interface UserService {

    fun changeDisplayId(userId: String, displayId: String)

    fun changeName(userId: String, name: String)

    fun getProfile(userId: String): User.Profile

    fun updateProfile(userId: String, profile: User.Profile)

}