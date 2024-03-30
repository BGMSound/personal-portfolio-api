package kr.bgmsound.bgmlab.persistence.repository.impl

import kr.bgmsound.bgmlab.authentication.NativeAccount
import kr.bgmsound.bgmlab.authentication.UserNativeAccountRepository
import org.springframework.stereotype.Repository

@Repository
class UserNativeAccountRepositoryImpl : UserNativeAccountRepository {

    override fun findByUserId(userId: String): NativeAccount? {
        TODO("Not yet implemented")
    }

    override fun save(account: NativeAccount) {
        TODO("Not yet implemented")
    }
}