package aibytests.openai

import aibytests.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KoreanEmbeddingTest : UnitTest() {
    private val sut = OpenAIApiClient()

    @Test
    fun semanticSimilarityIsHighForSimilarSentences() {
        val a = sut.getEmbeddings("이순신 장군은 임진왜란 때 선조의 견제를 받으면서도 나라를 지키셨다.")
        val b = sut.getEmbeddings("임진왜란 때 선조의 결제를 받았지만, 결국 나라를 지킨 것은 이순신 장군이다.")

        val result = similarity(a, b)
        log(result)

        assertThat(result).isGreaterThan(0.7)
    }

    @Test
    fun semanticSimilarityIsLowForUnrelatedSentences() {
        val a = sut.getEmbeddings("김문수, 국민의힘 후임 비대위원장에 초선 김용태 의원 내정")
        val b = sut.getEmbeddings("임베딩 간 유사도 점수를 평가하는 보통 기준은 다음과 같다")

        val result = similarity(a, b)
        log(result)

        assertThat(result).isLessThan(0.3)
    }
}