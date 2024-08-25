package org.example.tgnotification.modules.subscription

import jakarta.transaction.Transactional
import org.example.tgnotification.modules.subscription.entities.ChannelsEntity
import org.example.tgnotification.modules.subscription.entities.SubscriptionsEntity
import org.example.tgnotification.modules.subscription.repositories.SourcesRepository
import org.example.tgnotification.modules.subscription.repositories.ChannelsRepository
import org.example.tgnotification.modules.subscription.repositories.SubscriptionsRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class SubscriptionService (
    private val channelsRepository: ChannelsRepository,
    private val sourcesRepository: SourcesRepository,
    private val subscriptionsRepository: SubscriptionsRepository
) {
    val logger = LoggerFactory.getLogger(SubscriptionService::class.java)

    @Transactional
    fun addSubscription(channelId: String, sourceId: String): Boolean {
        val now = java.time.LocalDateTime.now(java.time.Clock.systemUTC())
        val channel = channelsRepository.findById(channelId).orElse(ChannelsEntity().apply {
            id = channelId
            createdAt = now
            updatedAt = now
        })

        val source = sourcesRepository.findById(sourceId)
        if (source.isEmpty) {
            throw Exception("Source not found")
        }
        val src = source.get()

        channelsRepository.save(channel)

        subscriptionsRepository.findByChannelAndSource(channel, src)?.let {
            logger.info("Subscription already exists")
            return false
        }

        subscriptionsRepository.save(SubscriptionsEntity(channel, src).apply {})

        return true
    }

    @Transactional
    fun getChannelsBySource(source: String): List<ChannelsEntity> {
        val sourceRes = sourcesRepository.findById(source)
        if (sourceRes.isEmpty) {
            throw Exception("Source not found")
        }
        val src = subscriptionsRepository.findAllBySource(sourceRes.get())
        if (src.isEmpty()) {
            return listOf()
        }
        return src.map { it.channel!! }
    }
}
