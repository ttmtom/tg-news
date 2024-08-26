package org.example.tgnotification.modules.tg.admin

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import kotlinx.coroutines.runBlocking
import org.example.tgnotification.modules.kafka.data.AdminMessage
import org.example.tgnotification.modules.tg.TGService
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class AdminService(
    private val tgService: TGService,
) {
    private val logger = LoggerFactory.getLogger(AdminService::class.java)
    val mapper = jacksonObjectMapper()

    @KafkaListener(topics = ["admin"], groupId = "\${spring.kafka.consumer.group-id}")
    fun mainConsumer(message: String) {
        logger.info("Received message: $message")
        val adminMessage: AdminMessage = mapper.readValue(message, AdminMessage::class.java)
        runBlocking {
            try {
                tgService.sendMessageToSubscriber("admin", adminMessage.text)
            } catch (e: Exception) {
                logger.error("Error on admin kafka listener")
                logger.error(e.toString())
                logger.error(e.stackTraceToString())
            }
        }
    }
}