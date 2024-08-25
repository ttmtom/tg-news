package org.example.tgnotification.modules.subscription.entities

import jakarta.persistence.*
import org.example.tgnotification.utils.common.DBBaseObject

@Entity
@Table(name = "sources")
open class SourcesEntity: DBBaseObject() {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    open var id: String? = null

    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="channel_source_mapping",
        joinColumns = [JoinColumn(name = "source_id")],
        inverseJoinColumns = [JoinColumn(name = "channel_id")],
    )
    val channels: MutableSet<ChannelsEntity> = mutableSetOf()

    override fun toString(): String {
        val createdAtStr = createdAt.toString() ?: ""
        val updatedAtStr = updatedAt.toString() ?: ""
        return "sourceId: $id \n" +
                "channels: ${channels.joinToString { it.id.toString() }} \n" +
                "createdAt: $createdAtStr \n" +
                "updatedAt: $updatedAtStr"
    }
}