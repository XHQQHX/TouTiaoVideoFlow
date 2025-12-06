package com.example.toutiaovideoflow.data.repository

import com.example.toutiaovideoflow.data.model.VideoItem

class VideoRepository {

    private val mockList = listOf(
        VideoItem(
            id = "1",
            url = "https://vd3.bdstatic.com/mda-mg7u9u1z4tij1z4s/720p/h264/1692342152593425472/mda-mg7u9u1z4tij1z4s.mp4",
            author = "张三",
            title = "城市街拍纪实",
            likeCount = "120",
            commentCount = "24",
            collectCount = "15"
        ),
        VideoItem(
            id = "2",
            url = "https://vd2.bdstatic.com/mda-mg7u8e4y3i1z0u8v/720p/h264/1692342110301936384/mda-mg7u8e4y3i1z0u8v.mp4",
            author = "李四",
            title = "野外露营剪辑",
            likeCount = "340",
            commentCount = "89",
            collectCount = "50"
        )
    )

    fun loadPage(page: Int, pageSize: Int = 2): List<VideoItem> {
        return mockList
    }
}
