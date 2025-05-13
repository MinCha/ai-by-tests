package aibytests.openai

import aibytests.Factory
import com.theokanning.openai.embedding.EmbeddingRequest

class OpenAIApiClient {
    private val client = Factory.OpenAiService

    fun getEmbeddings(text: String): List<Double> {
        val request = EmbeddingRequest.builder()
            .model("text-embedding-3-large")
            .input(listOf(text))
            .build()

        val response = client.createEmbeddings(request)

        return response.data[0].embedding
    }
}