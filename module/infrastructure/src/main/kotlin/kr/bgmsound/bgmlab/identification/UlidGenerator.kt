package kr.bgmsound.bgmlab.identification

import com.github.f4b6a3.ulid.UlidCreator
import kr.bgmsound.bgmlab.output.identification.IdentifierGenerator
import org.springframework.stereotype.Component

@Component
class UlidGenerator : IdentifierGenerator {
    override fun generateIdentifier(): String {
        return UlidCreator.getMonotonicUlid().toString()
    }
}