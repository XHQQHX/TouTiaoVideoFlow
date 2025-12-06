package com.example.toutiaovideoflow.data.repository

import com.example.toutiaovideoflow.data.model.VideoItem

class VideoRepository {
    private val videoUrlList = listOf(
        "http://vjs.zencdn.net/v/oceans.mp4",
        "https://media.w3.org/2010/05/sintel/trailer.mp4",
        "https://sf1-cdn-tos.huoshanstatic.com/obj/media-fe/xgplayer_doc_video/mp4/xgplayer-demo-360p.mp4",
        "https://www.w3school.com.cn/example/html5/mov_bbb.mp4",
        "https://www.w3schools.com/html/movie.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209105011F0zPoYzHry.mp4",
        "https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209104902N3v5Vpxuvb.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/ccff07ce5285890807898977876/v.f42906.mp4",
        "https://vod.pipi.cn/fe5b84bcvodcq1251246104/658e4b085285890797861659749/f0.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/6715a2145285890808041382798/v.f42906.mp4",
        "https://vod.pipi.cn/43903a81vodtransgzp1251246104/bbd4f07a5285890808066187974/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/84ec486e5285890807863862400/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/bb68c7515285890807928280731/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/87d0caf85285890807055577675/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/720db5425285890808000731940/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/67c6e6575285890807968082814/v.f42906.mp4",
        "https://vod.pipi.cn/43903a81vodtransgzp1251246104/2d1fc7685285890807909124510/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/2cb008ef5285890807135914942/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/c3f671d05285890807168094119/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/e1b5eeea5285890806379037311/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/f904d50d5285890806304637095/v.f42906.mp4",
        "https://vod.pipi.cn/fec9203cvodtransbj1251246104/aa5308fc5285890804986750388/v.f42906.mp4",
    )
    private val videoList = listOf(
        VideoItem(
            id = "1",
            url = "https://www.w3schools.com/html/movie.mp4",
            author = "张三",
            title = "测试用视频流1",
            likeCount = "120",
            commentCount = "24",
            collectCount = "15"
        ),
        VideoItem(
            id = "2",
            url = "https://stream7.iqilu.com/10339/upload_transcode/202002/09/20200209105011F0zPoYzHry.mp4",
            author = "李四",
            title = "测试用视频流2",
            likeCount = "340",
            commentCount = "89",
            collectCount = "50"
        )
    )

    fun loadPage(page: Int = 0, pageSize: Int = 2): List<VideoItem> {
        return videoList
    }
}
