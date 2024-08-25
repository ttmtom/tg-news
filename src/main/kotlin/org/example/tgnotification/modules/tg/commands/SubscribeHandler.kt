package org.example.tgnotification.modules.tg.commands

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import org.example.tgnotification.modules.subscription.SubscriptionService
import org.example.tgnotification.modules.tg.TGService
import org.example.tgnotification.utils.env.EnvProperties
import org.slf4j.LoggerFactory

@HandlerComponent
class SubscribeHandler(
    private val envProperties: EnvProperties,
    private val tgService: TGService,
    private val subscriptionService: SubscriptionService
) : BotHandler({
    val logger = LoggerFactory.getLogger(SubscribeHandler::class.java)

    command("/subscribe", next="auth") {
        logger.info("----- subscribe command -----")
        val senderId = message.from?.id
        logger.info("User: $senderId")
        if (senderId != envProperties.telegramBotAdminId.toLong()) {
            logger.error("User $senderId is not allowed to use this command")
            logger.error(message.from.toString())
            sendMessage("You are not allowed to use this command")
        } else {
            sendMessage("Please enter the channel id and source to sub in the following format: {channelId source}")
            next("subscribe")
        }
    }
    step("subscribe") {
        val (channelId, source) = text.split(" ")
        if (channelId.isEmpty() || source.isEmpty()) {
            sendMessage("Invalid input \n" +
                    "channel id: $channelId \n" +
                    "source: $source\n")
            return@step
        }

        try {
            subscriptionService.addSubscription(channelId, source)
            logger.info("Subscribed $source to $channelId")

            tgService.sendMessageToChannel(channelId, "You have subscribed to notifications")
            sendMessage("Subscribed $text")
        } catch (e: NullPointerException) {
            // library error??
            sendMessage(text)
        } catch (e: Exception) {
            logger.error("channelId Error: $e")
            sendMessage("${e.message}, ${e.localizedMessage}")
        }
    }
})
