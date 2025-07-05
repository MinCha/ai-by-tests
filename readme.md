# AI By Tests

This project provides examples guided by tests for developers who are interested in AI but are not yet familiar with it. Through reading test cases, you can understand various application-level AI use cases, such as RAG (Retrieval-Augmented Generation), embeddings, and other functionalities provided by the OpenAI platform.

### Examples (More to Come)
1. [KoreanEmbeddingTest](src/test/kotlin/aibytests/openai/KoreanEmbeddingTest.kt)
   * Demonstrates how similar meanings yield high similarity scores, even when different words are used and the language is Korean.
   * Shows embedding-based semantic search capabilities.
2. [BasicRAGTest](src/test/kotlin/aibytests/openai/BasicRAGTest.kt)
   * Set up simple documents in memory for clarity
   * Search for a relevant document using the query.
   * Generate a response with the retrieved content.

### Configuration
* Tests require an OpenAI API key.
* Set the environment variable `OPENAI_API_KEY` (or `openai_api_key`) or edit `src/main/resources/application.yml` to provide the key.

