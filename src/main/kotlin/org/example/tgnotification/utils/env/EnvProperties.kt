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

    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var kafkaBootstrapServers: String

    @Value("\${spring.kafka.producer.key-serializer}")
    lateinit var kafkaProducerKeySerializer: String

    @Value("\${spring.kafka.producer.value-serializer}")
    lateinit var kafkaProducerValueSerializer: String

    @Value("\${spring.kafka.consumer.key-deserializer}")
    lateinit var kafkaConsumerKeyDeserializer: String

    @Value("\${spring.kafka.consumer.value-deserializer}")
    lateinit var kafkaConsumerValueDeserializer: String

    @Value("\${spring.kafka.consumer.group-id}")
    lateinit var kafkaGroupId: String
}
