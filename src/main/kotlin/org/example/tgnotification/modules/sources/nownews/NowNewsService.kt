package org.example.tgnotification.modules.sources.nownews

import org.example.tgnotification.modules.kafka.producer.ProducerService
import org.example.tgnotification.modules.sources.SourceType
import org.example.tgnotification.modules.subscription.SubscriptionService
import org.jsoup.HttpStatusException
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class NowNewsService (
    private val subscriptionService: SubscriptionService,
    private val producerService: ProducerService,
) {
    private val logger = LoggerFactory.getLogger(NowNewsService::class.java)
    private val CATEGORY_LIST = listOf(
        "港聞",
        "兩岸國際",
        "生活",
        "科技",
        "財經"
    )

    private val FILETER_LIST = listOf(
        "時事全方位"
    )

    fun getNewsById(id: Int): Document? {
        try {
            val doc = Jsoup.connect("https://news.now.com/mobile/local/player?newsId=${id.toString()}")
                .header("User-Agent", "Mozilla/5.0 (Linux; Android 10; K) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/128.0.6613.99 Mobile Safari/537.36")
                .get()
            return doc
        } catch (e: Exception) {
            return null
        }
    }

    @Scheduled(fixedRate = 5 * 60 * 1000)
    fun fetchNews() {
        logger.info("fetch news")
        try {
            val source = subscriptionService.getSourceById(SourceType.now_news)
            val nowNewsDBData = (source.data ?: NowNewsDBData(574249)) as NowNewsDBData
            var todo = nowNewsDBData.latestNewsId

            while (true) {
                logger.info("fetching newsId: $todo")
                val doc = getNewsById(todo)
                if (doc == null) {
                    logger.info("doc is empty skipping")
                    Thread.sleep(2000)
                    break
                }
                val newsContent = doc.getElementById("newsDetailsSwipe")
                if (newsContent == null) {
                    logger.info("newsContent is empty skipping")
                    Thread.sleep(2000)
                    break
                }

                val titleDoc = doc.getElementsByClass("newsTitle")
                val title = titleDoc[0].text()

                if (FILETER_LIST.any { title.contains(it) }) {
                    todo += 1
                    Thread.sleep(5000)
                    continue
                }

                val activeCategory = doc.getElementsByClass("active")
                if (activeCategory.isEmpty()) {
                    todo += 1
                    Thread.sleep(5000)
                    continue
                }
                val category = activeCategory[0].getElementsByClass("label")[0]
                    .text()
                if (!CATEGORY_LIST.contains(category)) {
                    todo += 1
                    Thread.sleep(5000)
                    continue
                }

                val mediaDoc = doc.getElementsByClass("newsImgWrap")
                var img: String? = null

                if (!mediaDoc.isEmpty()) {
                    img = mediaDoc[0].getElementsByTag("img").attr("src")
                }

                val contentDoc = doc.getElementsByClass("newsLeading")
                val content = contentDoc[0].getElementsByClass("contentTxt")[0].text()

                val message = NowNewsMessage(
                    title,
                    img,
                    url="https://news.now.com/mobile/local/player?newsId=${todo.toString()}",
                    category,
                    reviewContent = content
                )
                logger.info("sending message")
                logger.info(NowNewsMessage::class.java.toString())
                producerService.sendMessage(SourceType.now_news.toString(), message)

                todo += 1
                Thread.sleep(5000)
            }
            subscriptionService.updateSource(SourceType.now_news, nowNewsDBData.copy(latestNewsId = todo))
        } catch (e: Exception) {
            logger.error("Error on fetch news")
            logger.error(e.toString())
            logger.error(e.stackTraceToString())
        }
    }
}
