package org.example.tgnotification.modules.subscription

import jakarta.persistence.*

@Entity
@Table(name = "sources")
open class Sources {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    open val id: String? = null

    @ManyToMany
    @JoinTable(
        name="subscriber_source",
        joinColumns = [JoinColumn(name = "source_id")],
        inverseJoinColumns = [JoinColumn(name = "subscriber_id")]
    )
    val subscribers: MutableSet<Subscribers> = mutableSetOf()
}