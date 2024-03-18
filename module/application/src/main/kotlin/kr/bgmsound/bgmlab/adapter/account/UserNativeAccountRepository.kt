package kr.bgmsound.bgmlab.adapter.account

import kr.bgmsound.bgmlab.dto.account.NativeAccount

interface UserNativeAccountRepository {

    fun findByDisplayId(displayId: String): NativeAccount?

    fun save(account: NativeAccount)

}