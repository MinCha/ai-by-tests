package aibytests

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ConfigTest : UnitTest() {
    @Test
    fun canGetKey() {
        assertThat(Config.name).isEqualTo("ai-by-tests")
    }
}