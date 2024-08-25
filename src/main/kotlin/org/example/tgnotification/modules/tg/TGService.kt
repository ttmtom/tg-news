package org.example.tgnotification.modules.tg

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import org.example.tgnotification.modules.tg.commands.SubscribeHandler
import org.example.tgnotification.utils.env.EnvProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class TGService(private val envProperties: EnvProperties) {
    val bot: TelegramBot = TelegramBotFactory.createTelegramBot(
        token = envProperties.telegramBotToken,
        username = envProperties.telegramBotUsername
    )
    val logger: Logger = LoggerFactory.getLogger(SubscribeHandler::class.java)

    suspend fun sendMessage(chatId: Long, text: String): Message {
        return bot.sendMessage(chatId, text)
    }

    suspend fun sendMessageToChannel(channelId: String, text: String): Message {
        logger.info("Sending message to channel $channelId")
        try {
            val res = bot.sendMessage(channelId, text)
            return res
        } catch (e: Exception) {
            logger.error("sendMessageToChannel Error: $e")
            throw e
        }
    }
}
