package kr.bgmsound.bgmlab.input.strategy

import kr.bgmsound.bgmlab.dto.SocialLoginResultDto
import kr.bgmsound.bgmlab.model.User

interface UserCreationStrategy {

    fun createNewSocialUser(socialLoginResult: SocialLoginResultDto): User

}