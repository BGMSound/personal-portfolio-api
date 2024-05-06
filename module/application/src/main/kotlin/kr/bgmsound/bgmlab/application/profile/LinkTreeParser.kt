package kr.bgmsound.bgmlab.application.profile

import java.net.URL

interface LinkTreeParser {

    fun parseLinkTree(linkTree: List<String>): List<URL>

}