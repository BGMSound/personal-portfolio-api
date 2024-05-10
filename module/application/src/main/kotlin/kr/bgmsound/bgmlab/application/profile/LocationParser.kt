package kr.bgmsound.bgmlab.application.profile

import kr.bgmsound.bgmlab.model.Profile

interface LocationParser {

    fun parseLocation(location: String): Profile.Location

}