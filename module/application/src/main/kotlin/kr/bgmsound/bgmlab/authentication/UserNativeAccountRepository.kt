package kr.bgmsound.bgmlab.authentication

import kr.bgmsound.bgmlab.authentication.dto.NativeAccount

interface UserNativeAccountRepository {

    fun findByDisplayId(displayId: String): NativeAccount?

    fun save(account: NativeAccount)

}