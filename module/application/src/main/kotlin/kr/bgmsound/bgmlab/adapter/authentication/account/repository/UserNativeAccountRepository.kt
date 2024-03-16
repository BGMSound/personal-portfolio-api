package kr.bgmsound.bgmlab.adapter.authentication.account.repository

import kr.bgmsound.bgmlab.dto.account.NativeAccount

interface UserNativeAccountRepository {

    fun findByDisplayId(displayId: String): NativeAccount?

    fun save(account: NativeAccount)

}