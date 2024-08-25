package org.example.tgnotification.modules.subscription.repositories

import org.example.tgnotification.modules.subscription.entities.ChannelsEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChannelsRepository: JpaRepository<ChannelsEntity, String>
