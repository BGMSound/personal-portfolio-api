package kr.bgmsound.bgmlab.application.profile

import org.springframework.stereotype.Component
import java.net.URL

@Component
class DefaultLinkTreeParser : LinkTreeParser {
    override fun parseLinkTree(linkTree: List<String>): List<URL> {
        return linkTree.map { URL(it) }
    }
}