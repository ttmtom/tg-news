package org.example.tgnotification.modules.subscription.entities

import jakarta.annotation.Nullable
import jakarta.persistence.*
import org.example.tgnotification.modules.sources.SourceBaseClass
import org.example.tgnotification.utils.common.DBBaseObject
import org.hibernate.annotations.JdbcTypeCode
import org.hibernate.type.SqlTypes

@Entity
@Table(name = "sources")
open class SourcesEntity: DBBaseObject() {
    @Id
    @Column(name = "id", nullable = false, length = 64)
    open var id: String? = null

    @JdbcTypeCode(SqlTypes.JSON)
    @Nullable
    var data: SourceBaseClass? = null

    override fun toString(): String {
        val createdAtStr = createdAt.toString() ?: ""
        val updatedAtStr = updatedAt.toString() ?: ""
        return "sourceId: $id \n" +
                "createdAt: $createdAtStr \n" +
                "updatedAt: $updatedAtStr"
    }
}
