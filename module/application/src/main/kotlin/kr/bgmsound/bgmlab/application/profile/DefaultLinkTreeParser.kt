package kr.bgmsound.bgmlab.application.profile

import kr.bgmsound.bgmlab.error.exception.InvalidLinkFormatException
import org.springframework.stereotype.Component
import java.net.URL

@Component
class DefaultLinkTreeParser : LinkTreeParser {
    override fun parseLinkTree(linkTree: List<String>): List<URL> {
        return runCatching { linkTree.map { URL(it) } }.getOrElse { throw InvalidLinkFormatException() }
    }
}