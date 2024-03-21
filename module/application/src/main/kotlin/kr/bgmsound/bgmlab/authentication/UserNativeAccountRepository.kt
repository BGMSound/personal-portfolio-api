package kr.bgmsound.bgmlab.authentication

interface UserNativeAccountRepository {

    fun findByUserId(userId: String): NativeAccount?

    fun save(account: NativeAccount)

}