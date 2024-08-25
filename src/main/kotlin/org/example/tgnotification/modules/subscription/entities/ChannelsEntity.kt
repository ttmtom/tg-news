package org.example.tgnotification.modules.subscription.entities

import jakarta.persistence.*
import org.example.tgnotification.utils.common.DBBaseObject

@Entity
@Table(name = "channels")
open class ChannelsEntity: DBBaseObject() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: String? = null

    override fun toString(): String {
        val createdAtStr = createdAt.toString() ?: ""
        val updatedAtStr = updatedAt.toString() ?: ""
        return "channelId: $id \n" +
                "createdAt: $createdAtStr \n" +
                "updatedAt: $updatedAtStr"
    }
}