package org.example.tgnotification.modules.subscription.entities

import jakarta.persistence.*
import org.example.tgnotification.utils.common.DBBaseObject

@Entity
@Table(name = "sources")
open class SourcesEntity: DBBaseObject() {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    open var id: String? = null

    override fun toString(): String {
        val createdAtStr = createdAt.toString() ?: ""
        val updatedAtStr = updatedAt.toString() ?: ""
        return "sourceId: $id \n" +
                "createdAt: $createdAtStr \n" +
                "updatedAt: $updatedAtStr"
    }
}
