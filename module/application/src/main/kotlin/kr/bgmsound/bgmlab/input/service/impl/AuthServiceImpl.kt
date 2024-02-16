package kr.bgmsound.bgmlab.input.service.impl

import kr.bgmsound.bgmlab.input.service.AuthService
import kr.bgmsound.bgmlab.LoginProviderType
import kr.bgmsound.bgmlab.dto.SocialUserDto
import kr.bgmsound.bgmlab.dto.Token
import kr.bgmsound.bgmlab.exception.AuthenticationFailException
import kr.bgmsound.bgmlab.model.User
import kr.bgmsound.bgmlab.model.Role
import kr.bgmsound.bgmlab.output.generator.IdentifierGenerator
import kr.bgmsound.bgmlab.output.provider.LoginProviderManager
import kr.bgmsound.bgmlab.output.provider.TokenProvider
import kr.bgmsound.bgmlab.repository.UserRepository
import kr.bgmsound.bgmlab.repository.UserSocialAccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
class AuthServiceImpl(
    private val loginProviderManager: LoginProviderManager,
    private val tokenProvider: TokenProvider,
    private val identifierGenerator: IdentifierGenerator,

    private val userRepository: UserRepository,
    private val userSocialAccountRepository: UserSocialAccountRepository,
) : AuthService {

    @Transactional
    override fun socialLogin(type: LoginProviderType, code: String): Pair<SocialUserDto, Token> {
        val provider = loginProviderManager.getSocialLoginProvider(provider = type)
        val result = try { provider.login(code) } catch (e: Exception) { throw AuthenticationFailException() }

        val socialUser = userSocialAccountRepository.findBySocialId(provider = type.name, socialId = result.socialId)
            ?.toSocialUser()
            ?: registerAndGetNewSocialUser(type, result.socialId).toSocialUser()

        val token = Token.of(
            accessToken = tokenProvider.createAccessToken(socialUser.id, socialUser.roles),
            refreshToken = tokenProvider.createRefreshToken(socialUser.id, socialUser.roles)
        )
        return socialUser to token
    }

    private fun createNewSocialUser(type: LoginProviderType, code: String): User {
        val displayId = "${type.name}${code}".lowercase()
        return User(
            id = identifierGenerator.generateIdentifier(),
            displayId = displayId,
            name = displayId,
            roles = listOf(Role.NEED_SIGNUP),
            createAt = LocalDateTime.now()
        )
    }

    private fun registerAndGetNewSocialUser(type: LoginProviderType, code: String): User {
        if(type == LoginProviderType.NATIVE) throw IllegalArgumentException("Native login is not supported")

        val user = createNewSocialUser(type, code)
        userRepository.save(user)
        userSocialAccountRepository.save(user.id, User.SocialAccount(provider = type.name, socialId = code))
        return user
    }

    private fun User.toSocialUser()
    = SocialUserDto(
        id = this.id,
        name = this.name,
        roles = this.roles,
        isNewUser = this.roles.any { it == Role.NEED_SIGNUP }
    )
}