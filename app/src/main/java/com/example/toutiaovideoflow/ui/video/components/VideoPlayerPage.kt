package com.example.toutiaovideoflow.ui.video.components

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.player.VideoPlayer
import com.example.toutiaovideoflow.ui.theme.PitchBlack
import kotlinx.coroutines.delay

@androidx.annotation.OptIn(UnstableApi::class)
@Composable
fun VideoPlayerPage(
    item: VideoItem,
    videoPlayer: VideoPlayer,
    isCurrentPage: Boolean
) {
    val context = LocalContext.current

    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    var isDragging by remember { mutableStateOf(false) }
    var videoAspectRatio by remember { mutableFloatStateOf(1f) }

    // 获取播放器实例
    val exoPlayer = remember { videoPlayer.getPlayer() ?: videoPlayer.initPlayer(item.url) }

    // 每次切换视频时更新 MediaItem
    LaunchedEffect(item.id) {
        exoPlayer.setMediaItem(MediaItem.fromUri(item.url))
        exoPlayer.prepare()
    }

    LaunchedEffect(isCurrentPage) {
        if (isCurrentPage) {
            exoPlayer.play()  // 当前页面才播放
        } else {
            exoPlayer.pause()  // 非当前页面暂停
        }
    }

    // 播放器监听器
    DisposableEffect(exoPlayer) {
        val listener = object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_READY) {
                    duration = exoPlayer.duration
                }
            }

            override fun onVideoSizeChanged(videoSize: androidx.media3.common.VideoSize) {
                videoAspectRatio = videoSize.width.toFloat() / videoSize.height.toFloat()
            }

            override fun onIsPlayingChanged(playing: Boolean) {
                isPlaying = playing
            }
        }
        exoPlayer.addListener(listener)

        onDispose {
            exoPlayer.pause()
            exoPlayer.removeListener(listener)
        }
    }

    // 更新播放进度
    LaunchedEffect(isPlaying, isDragging) {
        while (isPlaying && !isDragging) {
            currentPosition = exoPlayer.currentPosition
            delay(100)
        }
    }

    Box(
        modifier = Modifier.fillMaxSize().background(PitchBlack)
    ) {
        // 播放器视图
        AndroidView(
            factory = {
                PlayerView(context).apply {
                    player = exoPlayer
                    layoutParams = ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                    useController = false
                }
            },
            update = { playerView ->
                playerView.resizeMode =
                    if (videoAspectRatio < 1f)
                        AspectRatioFrameLayout.RESIZE_MODE_ZOOM
                    else
                        AspectRatioFrameLayout.RESIZE_MODE_FIT
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
            onSeek = { pos ->
                currentPosition = pos
                exoPlayer.seekTo(pos)
            },
            onPlayPause = {
                if (isPlaying) exoPlayer.pause() else exoPlayer.play()
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
