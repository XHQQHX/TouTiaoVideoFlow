package com.example.toutiaovideoflow.data.repository

import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.utils.RandomUtils.generateRandomNumber
import com.example.toutiaovideoflow.utils.RandomUtils.getRandomAuth
import com.example.toutiaovideoflow.utils.RandomUtils.getRandomImageUrl
import com.example.toutiaovideoflow.utils.RandomUtils.getRandomVideoUrl
//数据仓库
class VideoRepository {
    fun createVideoItem(id: String): VideoItem {
        return VideoItem(
            id = id,
            url = getRandomVideoUrl(),
            author = getRandomAuth(),
            title = "测试用视频流$id",
            likeCount = generateRandomNumber(0,10000).toString(),
            commentCount = generateRandomNumber(0,10000).toString(),
            collectCount = generateRandomNumber(0,10000).toString()
        )
    }
    fun createImageItem(id: String): VideoItem {
        return VideoItem(
            id = id,
            url = getRandomImageUrl(),
            author = getRandomAuth(),
            title = "测试用图片流$id",
            likeCount = generateRandomNumber(0,10000).toString(),
            commentCount = generateRandomNumber(0,10000).toString(),
            collectCount = generateRandomNumber(0,10000).toString(),
            isImage = true,
        )
    }
    fun createVideoList(count: Int): List<VideoItem> {
        val result = mutableListOf<VideoItem>()
        for (i in 1..count) {
            if(i%5==0){
                result.add(createImageItem(i.toString()))
            } else {
                result.add(createVideoItem(i.toString()))
            }
        }
        return result
    }
    private val allVideos = createVideoList(50)

    // cursor 分页：cursor 是下一页的起始 index
    fun loadPage(cursor: Int = 0, limit: Int = 5): Pair<List<VideoItem>, Int?> {

        if (cursor >= allVideos.size) {
            return emptyList<VideoItem>() to null
        }

        val end = (cursor + limit).coerceAtMost(allVideos.size)
        val list = allVideos.subList(cursor, end)

        val nextCursor = if (end < allVideos.size) end else null

        return list to nextCursor
    }
}
