package org.example.tgnotification.modules.tg

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import org.example.tgnotification.utils.env.EnvProperties
import org.springframework.stereotype.Service

@Service
class TGService(private val envProperties: EnvProperties) {
    val bot: TelegramBot = TelegramBotFactory.createTelegramBot(
        token = envProperties.telegramBotToken,
        username = envProperties.telegramBotUsername
    )

    suspend fun sendMessage(chatId: Long, text: String) {
        // send message to chat with chatId
        bot.sendMessage(chatId, text)
    }
}
