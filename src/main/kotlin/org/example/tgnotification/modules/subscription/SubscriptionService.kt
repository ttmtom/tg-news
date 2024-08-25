package org.example.tgnotification.modules.subscription

import jakarta.transaction.Transactional
import org.example.tgnotification.modules.subscription.entities.ChannelsEntity
import org.example.tgnotification.modules.subscription.repositories.SourcesRepository
import org.example.tgnotification.modules.subscription.repositories.ChannelsRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SubscriptionService (
    private val channelsRepository: ChannelsRepository,
    private val sourcesRepository: SourcesRepository
) {
    val logger = LoggerFactory.getLogger(SubscriptionService::class.java)

    @Transactional
    fun addSubscription(channelId: String, channelName: String): Boolean {
        val channel = channelsRepository.findById(channelId).orElse(ChannelsEntity().apply {
            id = channelId
            createdAt = java.time.LocalDateTime.now(java.time.Clock.systemUTC())
            updatedAt = java.time.LocalDateTime.now(java.time.Clock.systemUTC())
        })

        if (channel.sources.any({ it.id == channelName })) return true

        val source = sourcesRepository.findById(channelName)
        if (source.isEmpty) {
            throw Exception("Source not found")
        }
        val src = source.get()

        channel.sources.add(src)
        channelsRepository.save(channel)

        return true
    }
}
