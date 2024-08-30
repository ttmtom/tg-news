package org.example.tgnotification.modules.sources.nownews

data class NowNewsMessage(
    val title: String,
    val img: String?,
    val url: String,
    val category: String,
    val reviewContent: String
)
