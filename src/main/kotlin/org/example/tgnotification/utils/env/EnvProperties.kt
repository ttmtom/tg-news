package org.example.tgnotification.utils.env

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration

@Configuration
class EnvProperties {
    @Value("\${telegram-bot.token}")
    lateinit var telegramBotToken: String

    @Value("\${telegram-bot.username}")
    lateinit var telegramBotUsername: String

    @Value("\${telegram-bot.admin-id}")
    lateinit var telegramBotAdminId: String
}
