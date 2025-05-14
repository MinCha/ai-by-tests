package aibytests.openai

import aibytests.Factory
import com.theokanning.openai.completion.chat.ChatCompletionRequest
import com.theokanning.openai.completion.chat.ChatMessage
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

    fun getResponse(message: String, context: String, model: String = "gpt-3.5-turbo", temperature: Double = 0.0): String {
        val request = ChatCompletionRequest.builder()
            .model(model)
            .messages(
                listOf(
                    ChatMessage("system", context),
                    ChatMessage("user", message)
                )
            )
            .temperature(temperature)
            .build()

        val response = client.createChatCompletion(request)
        return response.choices[0].message.content
    }
}