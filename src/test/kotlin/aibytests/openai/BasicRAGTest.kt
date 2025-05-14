package aibytests.openai

import aibytests.UnitTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class BasicRAGTest : UnitTest() {
    private val sut = OpenAIApiClient()

    @Test
    fun responseIsMadeByReferencingRAG() {
        val documents = listOf(
            "Cha is born in 1980",
            "John is born in 1990.",
            "Tom is born in 2000"
        )
        val question = "What is the year of birth of Tom?"
        val questionVectors = sut.getEmbeddings(question)
        val relevantDocument = searchRelevantDocument(documents, questionVectors)
        log("${relevantDocument.document.text} (+${relevantDocument.score})")

        val result = sut.getResponse(question, relevantDocument.getText())
        log(result)

        assertThat(result.filter { it.isDigit() }).isEqualTo("2000")
    }

    private fun searchRelevantDocument(
        documents: List<String>,
        questionVectors: List<Double>
    ) = documents.map {
        Document(it, sut.getEmbeddings(it))
    }.map {
        DocumentScore(it, similarity(questionVectors, it.vectors))
    }.maxByOrNull { it.score }!!

    data class Document(val text: String, val vectors: List<Double>)
    data class DocumentScore(val document: Document, val score: Double) {
        fun getText(): String {
            return document.text
        }
    }
}