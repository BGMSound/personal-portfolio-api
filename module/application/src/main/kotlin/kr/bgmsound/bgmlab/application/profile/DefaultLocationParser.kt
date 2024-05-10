package kr.bgmsound.bgmlab.application.profile

import kr.bgmsound.bgmlab.error.exception.InvalidLocationFormatException
import kr.bgmsound.bgmlab.model.Profile
import org.springframework.stereotype.Component

@Component
class DefaultLocationParser : LocationParser {
    override fun parseLocation(location: String): Profile.Location {
        val trimmedLocation = location.trim()
        if(trimmedLocation.isEmpty() || trimmedLocation.isBlank() || trimmedLocation == ",") {
            throw InvalidLocationFormatException()
        }

        val splitter = location.split(", ")
        if(splitter.size != 2) {
            throw InvalidLocationFormatException()
        }
        val (city, country) = splitter
        return Profile.Location(city, country)
    }
}