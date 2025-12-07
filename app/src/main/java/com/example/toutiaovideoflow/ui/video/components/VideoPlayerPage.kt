package com.example.toutiaovideoflow.ui.video.components

import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.Player
import androidx.media3.ui.PlayerView
import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.player.VideoPlayer
import com.example.toutiaovideoflow.ui.theme.PitchBlack
import kotlinx.coroutines.delay

@Composable
fun VideoPlayerPage(item: VideoItem) {

    val context = androidx.compose.ui.platform.LocalContext.current
    val player = remember { VideoPlayer(context) }
    val exoPlayer = remember(item.url) { player.initPlayer(item.url) }

    var isPlaying by remember { mutableStateOf(exoPlayer.isPlaying) }
    var currentPosition by remember { mutableStateOf(0L) }
    var duration by remember { mutableStateOf(0L) }
    var isDragging by remember { mutableStateOf(false) }

    LaunchedEffect(exoPlayer) {
        exoPlayer.play()
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    duration = exoPlayer.duration
                    Log.d("VideoPlayerPage", "duration: $duration")
                }
            }

            override fun onIsPlayingChanged(playing: Boolean) {
                isPlaying = playing
            }
        })
    }
    LaunchedEffect(isPlaying) {
        while (isPlaying && !isDragging) {
            currentPosition = exoPlayer.currentPosition
            Log.d("VideoPlayerPage", "currentPosition: $currentPosition")
            delay(100) // 每100ms更新一次
        }
    }
    DisposableEffect(Unit) {
        // 页面可见时播放
        //exoPlayer.play()
        onDispose {
            exoPlayer.pause()
        }
    }

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
                    this.useController = false
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        // 顶部栏
        ControlBar(
            modifier = Modifier.fillMaxSize(),
            isPlaying = isPlaying,
            currentPosition = currentPosition,
            duration = duration,
            onSeek = { position ->
                currentPosition = position
                exoPlayer.seekTo(position)
            },
            onPlayPause = {
                if(isPlaying){exoPlayer.pause()}
                else{exoPlayer.play()}
            },
            onDraggingChanged = { dragging ->
                isDragging = dragging
            }
        )
        // 右侧浮动 UI
        VideoFloatingUI(
            modifier = Modifier.fillMaxSize(),
            item = item
        )
    }
}
