package org.example.tgnotification.modules.tg.commands

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.handler.BotHandler
import org.slf4j.LoggerFactory

@HandlerComponent
class StartHandler() : BotHandler({
    val logger = LoggerFactory.getLogger(StartHandler::class.java)

    command("/start") {
        sendMessage("Hello, my name is ${bot.username} :-)")
    }
    command("/help") {
        sendMessage("I'm a bot that can send you notifications. You can use the following commands:\n" +
                "/start - Say hi to you\n" +
                "/help - Show this help message\n" +
                "/subscribe - Subscribe to notifications\n")
    }
})
