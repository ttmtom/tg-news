package org.example.tgnotification.modules.kafka.consumer

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.example.tgnotification.utils.env.EnvProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory
import org.springframework.kafka.core.ConsumerFactory
import org.springframework.kafka.core.DefaultKafkaConsumerFactory

@EnableKafka
@Configuration
class KafkaConsumerConfig(
    private val envProperties: EnvProperties
) {
    @Bean
    fun createConsumer(): ConsumerFactory<String, Any> {
        val configProps = mapOf(
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG to envProperties.kafkaBootstrapServers,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG to envProperties.kafkaConsumerKeyDeserializer,
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG to envProperties.kafkaConsumerValueDeserializer,
        )
        return DefaultKafkaConsumerFactory(configProps)
    }

    @Bean
    fun kafkaListenerContainerFactory(): ConcurrentKafkaListenerContainerFactory<String, String> {
        val factory = ConcurrentKafkaListenerContainerFactory<String, String>()
        factory.consumerFactory = createConsumer()
        return factory
    }
}
