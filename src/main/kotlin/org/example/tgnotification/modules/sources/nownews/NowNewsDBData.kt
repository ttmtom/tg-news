package org.example.tgnotification.modules.sources.nownews

import org.example.tgnotification.modules.sources.SourceBaseClass
import org.example.tgnotification.modules.sources.SourceType

data class NowNewsDBData (
    val latestNewsId: Int,
): SourceBaseClass() {
    override val id = SourceType.now_news
}

