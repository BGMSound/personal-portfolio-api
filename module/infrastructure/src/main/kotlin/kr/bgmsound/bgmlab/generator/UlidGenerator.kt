package kr.bgmsound.bgmlab.generator

import com.github.f4b6a3.ulid.UlidCreator
import kr.bgmsound.bgmlab.output.generator.IdentifierGenerator
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component

@Primary
@Component
class UlidGenerator : IdentifierGenerator {
    override fun generateIdentifier(): String {
        return UlidCreator.getMonotonicUlid().toString()
    }
}