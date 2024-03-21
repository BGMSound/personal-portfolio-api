package kr.bgmsound.bgmlab.authentication

interface UserNativeAccountRepository {

    fun findByDisplayId(displayId: String): NativeAccount?

    fun save(account: NativeAccount)

}