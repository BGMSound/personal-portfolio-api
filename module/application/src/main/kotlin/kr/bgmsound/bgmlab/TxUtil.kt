package kr.bgmsound.bgmlab

import org.springframework.stereotype.Component
import org.springframework.transaction.annotation.Transactional

@Component
class TxUtil(
    txAdvice: TxAdvice,
) {

    init {
        tx = txAdvice
    }

    companion object {

        private lateinit var tx: TxAdvice

        fun <T> readWithTransaction(function: () -> T): T {
            return tx.readWithTransaction(function)
        }

        fun <T> writeWithTransaction(function: () -> T): T {
            return tx.writeWithTransaction(function)
        }
    }

    @Component
    class TxAdvice {

        @Transactional(readOnly = true)
        fun <T> readWithTransaction(function: () -> T): T {
            return function.invoke()
        }

        @Transactional
        fun <T> writeWithTransaction(function: () -> T): T {
            return function.invoke()
        }
    }
}