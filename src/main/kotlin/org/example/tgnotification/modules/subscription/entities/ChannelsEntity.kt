package org.example.tgnotification.modules.subscription.entities

import jakarta.persistence.*
import org.example.tgnotification.utils.common.DBBaseObject
import java.util.*

@Entity
@Table(name = "channels")
open class ChannelsEntity: DBBaseObject() {
    @Id
    @Column(name = "id", nullable = false)
    open var id: String? = null


    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name="channel_source_mapping",
        joinColumns = [JoinColumn(name = "channel_id")],
        inverseJoinColumns = [JoinColumn(name = "source_id")],
    )
    val sources: MutableSet<SourcesEntity> = mutableSetOf()

    override fun toString(): String {
        val createdAtStr = createdAt.toString() ?: ""
        val updatedAtStr = updatedAt.toString() ?: ""
        return "channelId: $id \n" +
                "sources: ${sources.joinToString { it.id.toString() }} \n" +
                "createdAt: $createdAtStr \n" +
                "updatedAt: $updatedAtStr"
    }
}