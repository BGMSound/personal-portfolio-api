package kr.bgmsound.bgmlab.api.profile

import kr.bgmsound.bgmlab.api.profile.dto.request.UpdateProfileRequest
import kr.bgmsound.bgmlab.application.profile.LinkTreeParser
import kr.bgmsound.bgmlab.application.profile.LocationParser
import kr.bgmsound.bgmlab.application.profile.dto.ProfilePatchDto
import org.springframework.stereotype.Component

@Component
class ProfileRequestMapper {
    fun map(request: UpdateProfileRequest) = ProfilePatchDto(
        name = request.name,
        profileImageUrl = request.profileImageUrl,
        description = request.description,
        email = request.email,
        location = request.location?.let { LocationParser.parseLocation(it) },
        organization = request.organization,
        linkTree = request.linkTree?.let { LinkTreeParser.parseLinkTree(it) },
        readMe = request.readMe
    )
}