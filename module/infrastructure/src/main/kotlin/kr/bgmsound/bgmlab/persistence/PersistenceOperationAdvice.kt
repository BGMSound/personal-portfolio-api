package kr.bgmsound.bgmlab.persistence

import kr.bgmsound.bgmlab.application.PersistenceOperationGateway
import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class PersistenceOperationAdvice : PersistenceOperationGateway {

    @Transactional(readOnly = true)
    override fun <T> readWithTransaction(function: () -> T): T {
        return function.invoke()
    }

    @Transactional
    override fun <T> writeWithTransaction(function: () -> T): T {
        return function.invoke()
    }
}