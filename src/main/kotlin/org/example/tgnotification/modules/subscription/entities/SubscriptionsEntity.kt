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
    open val id: Long = 0L

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "channel_id", nullable = false, unique = false)
    open var channel: ChannelsEntity? = null

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "source_id", nullable = false, unique = false)
    open var source: SourcesEntity? = null

    constructor(channel: ChannelsEntity, source: SourcesEntity) : this() {
        this.channel = channel
        this.source = source
    }
}
