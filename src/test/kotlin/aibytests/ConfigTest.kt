package aibytests

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

class ConfigTest : UnitTest() {
    @Test
    fun canGetKey() {
        Assertions.assertThat(Config.name).isEqualTo("ai-by-tests")
    }
}