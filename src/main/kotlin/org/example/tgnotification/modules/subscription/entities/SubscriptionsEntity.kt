package org.example.tgnotification.modules.subscription.entities

import jakarta.persistence.*

@Entity
@Table(
    name = "subscriptions",
    indexes = [Index(name = "idx_channel_id_source", columnList = "channel_id, source_id", unique = true)]
)
open class SubscriptionsEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    val id: Long = 0L

    @OneToOne
    @JoinColumn(name = "channel_id", nullable = false)
    var channel: ChannelsEntity? = null

    @OneToOne
    @JoinColumn(name = "source_id", nullable = false)
    var source: SourcesEntity? = null

    constructor(channel: ChannelsEntity, source: SourcesEntity) : this() {
        this.channel = channel
        this.source = source
    }
}
