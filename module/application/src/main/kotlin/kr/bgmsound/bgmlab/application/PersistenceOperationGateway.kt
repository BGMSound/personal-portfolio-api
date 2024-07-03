package kr.bgmsound.bgmlab.application

interface PersistenceOperationGateway {

    fun <T> readWithTransaction(function: () -> T): T

    fun <T> writeWithTransaction(function: () -> T): T

}