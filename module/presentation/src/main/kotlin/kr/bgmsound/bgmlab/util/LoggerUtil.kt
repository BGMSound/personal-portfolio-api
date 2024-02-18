package kr.bgmsound.bgmlab.util

import org.slf4j.LoggerFactory
import org.slf4j.Logger

inline fun <reified T> T.getLogger(): Logger = LoggerFactory.getLogger(T::class.java)!!
