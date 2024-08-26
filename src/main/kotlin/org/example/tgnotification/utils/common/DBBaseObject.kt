package org.example.tgnotification.utils.common

import jakarta.persistence.Column
import jakarta.persistence.MappedSuperclass
import org.hibernate.annotations.CreationTimestamp
import org.hibernate.annotations.UpdateTimestamp

@MappedSuperclass
open class DBBaseObject {
    @CreationTimestamp
    @Column(name="created_at", nullable = false, updatable = false)
    open lateinit var createdAt: java.time.LocalDateTime

    @UpdateTimestamp
    @Column(name="updated_at", nullable = false)
    open lateinit var updatedAt: java.time.LocalDateTime
}
