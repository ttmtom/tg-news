package org.example.tgnotification.modules.tg

import io.github.dehuckakpyt.telegrambot.TelegramBot
import io.github.dehuckakpyt.telegrambot.factory.TelegramBotFactory
import io.github.dehuckakpyt.telegrambot.model.telegram.Message
import jakarta.transaction.Transactional
import org.example.tgnotification.modules.subscription.SubscriptionService
import org.example.tgnotification.modules.tg.commands.SubscribeHandler
import org.example.tgnotification.utils.env.EnvProperties
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service


@Service
class TGService(
    private val envProperties: EnvProperties,
    private val subscriptionService: SubscriptionService
) {
    val bot: TelegramBot = TelegramBotFactory.createTelegramBot(
        token = envProperties.telegramBotToken,
        username = envProperties.telegramBotUsername
    )
    val logger: Logger = LoggerFactory.getLogger(TGService::class.java)

    suspend fun sendMessageToChannel(channelId: String, text: String): Message {
        logger.info("Sending message to channel $channelId")
        val res = bot.sendMessage(channelId, text)
        return res
    }

    @Transactional
    suspend fun sendMessageToSubscriber(source: String, text: String) {
        logger.info("Sending message to subscriber $source")
        val channels = subscriptionService.getChannelsBySource(source)
        logger.info("to channels: $channels")
        channels.forEach {
            logger.info("Sending message to ${it.id}")
            try {
                it.id?.let { it1 -> this.sendMessageToChannel(it1, text) }
            } catch (e: NullPointerException) {
                // library error??
                logger.info("sendMessageToSubscriber null Error: $e")
            } catch (e: Exception) {
                logger.error("sendMessageToSubscriber Error: $e")
                throw e
            }
        }
    }
}
