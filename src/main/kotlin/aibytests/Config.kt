package aibytests

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory

object Config {
    private val logger = LoggerFactory.getLogger(this::class.java)
    private val config: Map<String, Any> by lazy {
        val mapper = ObjectMapper(YAMLFactory()).registerKotlinModule()
        val inputStream = javaClass.classLoader.getResourceAsStream("application.yml")!!
        val javaType = mapper.typeFactory.constructMapType(Map::class.java, String::class.java, Any::class.java)
        mapper.readValue(inputStream, javaType)
    }
    private val common: Map<String, String> by lazy { getConfigMap("common") }
    private val openAI: Map<String, String> by lazy { getConfigMap("openai") }

    val openAiKey: String by lazy { System.getenv("openai.api-key") ?: openAI["api-key"]!! }
    val name: String by lazy { common["name"]!! }

    private fun getConfigMap(rootKey: String): Map<String, String> {
        @Suppress("UNCHECKED_CAST")
        return config[rootKey]!! as Map<String, String>
    }
}