package com.example.toutiaovideoflow.ui.video.components

import android.annotation.SuppressLint
import android.app.Activity
import android.content.res.Configuration
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.player.VideoPlayer
import com.example.toutiaovideoflow.ui.theme.PitchBlack
import kotlinx.coroutines.delay

@SuppressLint("LocalContextConfigurationRead")
@OptIn(UnstableApi::class)
@Suppress("COMPOSE_APPLIER_CALL_MISMATCH")
@Composable
fun VideoPlayerPage(item: VideoItem) {

    val context = androidx.compose.ui.platform.LocalContext.current
    val player = remember { VideoPlayer(context) }
    val exoPlayer = remember(item.url) { player.initPlayer(item.url) }

    val configuration = context.resources.configuration
    val isLandscape = configuration.orientation == Configuration.ORIENTATION_LANDSCAPE

    var isPlaying by remember { mutableStateOf(exoPlayer.isPlaying) }
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    var isDragging by remember { mutableStateOf(false) }
    var videoAspectRatio by remember { mutableFloatStateOf(1f) }

    LaunchedEffect(exoPlayer) {
        exoPlayer.play()
        exoPlayer.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    duration = exoPlayer.duration
                    Log.d("VideoPlayerPage", "duration: $duration")
                }
            }
            override fun onVideoSizeChanged(videoSize: androidx.media3.common.VideoSize) {
                videoAspectRatio = videoSize.width.toFloat() / videoSize.height.toFloat()
                Log.d("VideoPlayerScreen", "videoAspectRatio: $videoAspectRatio")
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
            update = { playerView ->
                if(videoAspectRatio < 1f){
                    playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                } else {
                    playerView.resizeMode = AspectRatioFrameLayout.RESIZE_MODE_FIT
                }
            },
            modifier = Modifier.fillMaxSize()
        )
        // 控制栏
        ControlBar(
            modifier = Modifier.fillMaxSize(),
            isPlaying = isPlaying,
            currentPosition = currentPosition,
            duration = duration,
            videoAspectRatio = videoAspectRatio,
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
            item = item,
            isDragging = isDragging,
            isPlaying = isPlaying
        )
    }
}
