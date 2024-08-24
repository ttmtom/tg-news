package org.example.tgnotification.modules.subscription

import jakarta.persistence.*

@Entity
@Table(name = "subscribers")
open class Subscribers {
    @Id
    @Column(name = "id", nullable = false)
    open var id: String? = null

    @ManyToMany
    @JoinTable(
        name="subscriber_source",
        joinColumns = [JoinColumn(name = "subscriber_id")],
        inverseJoinColumns = [JoinColumn(name = "source_id")]
    )
    val sources: MutableSet<Sources> = mutableSetOf()
}
