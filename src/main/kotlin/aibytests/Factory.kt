package aibytests

import com.theokanning.openai.service.OpenAiService

object Factory {
    val OpenAiService by lazy { OpenAiService(Config.openAiKey) }
}