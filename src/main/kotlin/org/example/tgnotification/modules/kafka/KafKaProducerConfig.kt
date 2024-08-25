package org.example.tgnotification.modules.kafka

import org.apache.kafka.clients.producer.ProducerConfig
import org.example.tgnotification.utils.env.EnvProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
class KafKaProducerConfig (
    private val envProperties: EnvProperties
) {
    @Bean
    fun producerFactory(): ProducerFactory<String, Any> {
        val configProps = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to envProperties.kafkaBootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to envProperties.kafkaProducerKeySerializer,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to envProperties.kafkaProducerValueSerializer
        )
        return DefaultKafkaProducerFactory(configProps)
    }

    @Bean
    fun kafkaTemplate(): KafkaTemplate<String, Any> {
        return KafkaTemplate(producerFactory())
    }
}