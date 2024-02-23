package kr.bgmsound.bgmlab.input.strategy

import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.model.User

interface UserCreationStategy {

    fun createNewSocialUser(socialLoginResult: SocialLoginResultDto): User

}