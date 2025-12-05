package com.example.toutiaovideoflow.data.model

data class VideoItem(
    val id: Int,
    val url: String,
    val title: String,
    val author: String,
    val avatar: String,
    val cover: String,
    val likeCount: Int,
    val commentCount: Int,
    val collectCount: Int
)