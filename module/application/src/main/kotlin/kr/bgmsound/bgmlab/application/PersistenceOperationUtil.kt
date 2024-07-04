package kr.bgmsound.bgmlab.application

import org.springframework.stereotype.Component

@Component
class PersistenceOperationUtil(
    persistenceOperationGateway: PersistenceOperationGateway
) {
    init {
        gateway = persistenceOperationGateway
    }

    companion object {

        private lateinit var gateway: PersistenceOperationGateway

        fun <T> readWithTransaction(function: () -> T): T {
            return gateway.readWithTransaction(function)
        }

        fun <T> writeWithTransaction(function: () -> T): T {
            return gateway.writeWithTransaction(function)
        }
    }
}