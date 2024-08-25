package org.example.tgnotification.modules.kafka

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.slf4j.LoggerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class ProducerService (
    private val kafkaTemplate: KafkaTemplate<String, Any>
) {
    private val logger = LoggerFactory.getLogger(ProducerService::class.java)
    val mapper = jacksonObjectMapper().registerKotlinModule()

    fun sendMessage(topic: String, message: Any) {
        logger.info("Sending message to topic: $topic")
        kafkaTemplate.send(topic, mapper.writeValueAsString(message))
    }
}