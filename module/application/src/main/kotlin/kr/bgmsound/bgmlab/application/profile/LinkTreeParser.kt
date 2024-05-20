package kr.bgmsound.bgmlab.application.profile

import kr.bgmsound.bgmlab.error.exception.InvalidLinkFormatException
import java.net.URL

class LinkTreeParser {
    companion object {
        fun parseLinkTree(linkTree: List<String>): List<URL> {
            return runCatching {
                linkTree.map { URL(it) }
            }.getOrElse {
                throw InvalidLinkFormatException()
            }
        }
    }
}