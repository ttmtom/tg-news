package org.example.tgnotification.modules.sources

import com.fasterxml.jackson.annotation.JsonSubTypes
import com.fasterxml.jackson.annotation.JsonTypeInfo
import org.example.tgnotification.modules.sources.nownews.NowNewsDBData

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "type"
)
@JsonSubTypes(
    JsonSubTypes.Type(value = NowNewsDBData::class, name = "NowNewsDBData"),
)
abstract class SourceBaseClass {
    abstract val id: SourceType
}
