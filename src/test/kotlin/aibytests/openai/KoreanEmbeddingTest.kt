package aibytests.openai

import aibytests.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class KoreanEmbeddingTest : UnitTest() {
    private val sut = OpenAIApiClient()
    val a = sut.getEmbeddings("이순신 장군은 임진왜란 때 선조의 견제를 받으면서도 나라를 지키셨다.")
    val b = sut.getEmbeddings("임진왜란 때 선조의 결제를 받았지만, 결국 나라를 지킨 것은 이순신 장군이다.")
    val c = sut.getEmbeddings("김문수, 국민의힘 후임 비대위원장에 초선 김용태 의원 내정")

    @Test
    fun semanticSimilarityIsHighForSimilarSentences() {
        val result = similarity(a, b)
        log(result)

        assertThat(result).isGreaterThan(0.7)
    }

    @Test
    fun semanticSimilarityIsLowForUnrelatedSentences() {
        val result = similarity(a, c)
        log(result)

        assertThat(result).isLessThan(0.3)
    }

    @Test
    fun searchReturnsCorrectRankingBasedOnSimilarity() {
        val keyword = sut.getEmbeddings("이순신 임진왜란")

        val scores = listOf(a, b, c).map { similarity(keyword, it) }
        log(scores)

        assertThat(scores[0]).isGreaterThan(scores[1])
        assertThat(scores[0]).isGreaterThan(scores[2])
        assertThat(scores[1]).isGreaterThan(scores[2])
    }
}