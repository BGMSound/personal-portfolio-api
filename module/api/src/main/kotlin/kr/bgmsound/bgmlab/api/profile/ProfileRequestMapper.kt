package kr.bgmsound.bgmlab.api.profile

import kr.bgmsound.bgmlab.api.profile.dto.request.UpdateProfileRequest
import kr.bgmsound.bgmlab.application.profile.LinkTreeParser
import kr.bgmsound.bgmlab.application.profile.LocationParser
import kr.bgmsound.bgmlab.model.Profile
import org.springframework.stereotype.Component

@Component
class ProfileRequestMapper(
    private val linkTreeParser: LinkTreeParser,
    private val locationParser: LocationParser
) {
    /*
     * Convert UpdateProfileRequest to Profile
     * when name or profile image url is empty, it won't be updated
     */
    fun map(request: UpdateProfileRequest) = Profile(
        name = request.name ?: "",
        profileImageUrl = request.profileImageUrl ?: "",
        description = request.description,
        email = request.email,
        location = request.location?.let { locationParser.parseLocation(it) },
        organization = request.organization,
        linkTree = linkTreeParser.parseLinkTree(request.linkTree),
        readMe = request.readMe
    )
}