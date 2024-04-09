package kr.bgmsound.bgmlab.application.authentication

interface UserNativeAccountRepository {

    fun findByUserId(userId: String): NativeAccount?

    fun save(account: NativeAccount)

}