package com.example.toutiaovideoflow.data.model
//视频基本元素
data class VideoItem(
    val id: String,
    val url: String,
    val author: String,
    val title: String,
    val likeCount: String,
    val commentCount: String,
    val collectCount: String
)
