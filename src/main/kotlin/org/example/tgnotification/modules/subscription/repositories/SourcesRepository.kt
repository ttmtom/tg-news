package org.example.tgnotification.modules.subscription.repositories

import org.example.tgnotification.modules.subscription.entities.SourcesEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SourcesRepository: JpaRepository<SourcesEntity, String> {
}