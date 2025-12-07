package com.example.toutiaovideoflow.data.repository

import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.utils.RandomUtils.generateRandomNumber
import com.example.toutiaovideoflow.utils.RandomUtils.getRandomAuth
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
    fun createVideoList(count: Int): List<VideoItem> {
        return (1..count).map { id ->
            createVideoItem(id.toString())
        }
    }
    private val videoList
        get() = createVideoList(10)

    fun loadPage(page: Int = 0, pageSize: Int = 2): List<VideoItem> {
        return videoList
    }
}
