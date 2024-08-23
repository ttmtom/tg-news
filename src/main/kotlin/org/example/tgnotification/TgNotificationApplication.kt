package org.example.tgnotification

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TgNotificationApplication

fun main(args: Array<String>) {
    runApplication<TgNotificationApplication>(*args)
}
