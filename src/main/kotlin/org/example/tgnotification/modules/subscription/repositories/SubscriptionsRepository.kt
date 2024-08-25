package org.example.tgnotification.modules.subscription.repositories

import org.example.tgnotification.modules.subscription.entities.ChannelsEntity
import org.example.tgnotification.modules.subscription.entities.SourcesEntity
import org.example.tgnotification.modules.subscription.entities.SubscriptionsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SubscriptionsRepository: JpaRepository<SubscriptionsEntity, Long> {
    fun findByChannelAndSource(channel: ChannelsEntity, source: SourcesEntity): SubscriptionsEntity?
    fun findAllBySource(source: SourcesEntity): List<SubscriptionsEntity>
}
