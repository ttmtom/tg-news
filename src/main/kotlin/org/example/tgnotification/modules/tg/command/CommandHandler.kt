package org.example.tgnotification.modules.tg.command

import io.github.dehuckakpyt.telegrambot.annotation.HandlerComponent
import io.github.dehuckakpyt.telegrambot.handler.BotHandler

@HandlerComponent
class CommandHandler : BotHandler({
    command("/start") {
        sendMessage("Hello, my name is ${bot.username} :-)")
    }
    command("/help") {
        sendMessage("I'm a bot that can send you notifications. You can use the following commands:\n" +
                "/start - Start the bot\n" +
                "/help - Show this help message\n" +
                "/subscribe - Subscribe to notifications\n" +
                "/unsubscribe - Unsubscribe from notifications")
    }
})
