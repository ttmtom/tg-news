package org.example.tgnotification.modules.tg.commands

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.exception.chat.ChatException
import io.github.dehuckakpyt.telegrambot.handler.BotUpdateHandler
import org.example.tgnotification.modules.kafka.ProducerService
import org.example.tgnotification.modules.kafka.data.AdminMessage
import org.example.tgnotification.modules.tg.TGService
import org.example.tgnotification.utils.env.EnvProperties
import org.slf4j.LoggerFactory

@HandlerComponent
class AdminMsgHandler(
    private val envProperties: EnvProperties,
    private val tgService: TGService,
    private val producerService: ProducerService
) : BotUpdateHandler({
    val logger = LoggerFactory.getLogger(AdminMsgHandler::class.java)

    message {
        logger.info("---- message received ----")
        if (text == null) {
            logger.error("Empty message")
            return@message
        }
        val senderId = from?.id
        if (senderId != envProperties.telegramBotAdminId.toLong()) throw ChatException("403")

//        tgService.sendMessageToSubscriber("admin", text.orEmpty())
        val message = AdminMessage(text.orEmpty())
        producerService.sendMessage("admin", message)
    }
})
