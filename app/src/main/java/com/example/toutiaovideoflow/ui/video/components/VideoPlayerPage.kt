package com.example.toutiaovideoflow.ui.video.components

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.ui.PlayerView
import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.player.VideoPlayer
import com.example.toutiaovideoflow.ui.theme.PitchBlack

@Composable
fun VideoPlayerPage(item: VideoItem) {

    val context = androidx.compose.ui.platform.LocalContext.current
    val player = remember { VideoPlayer(context) }
    val exoPlayer = remember(item.url) { player.initPlayer(item.url) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(PitchBlack)
    ) {

        AndroidView(
            factory = {
                PlayerView(context).apply {
                    this.player = exoPlayer
                    this.layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                }
            },
            modifier = Modifier.fillMaxSize()
        )

        // 右侧浮动 UI（已完成，不修改）
        VideoFloatingUI(
            modifier = Modifier.fillMaxSize(),
            item = item
        )
    }
}
