package com.example.toutiaovideoflow.ui.video.components

import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.ui.AspectRatioFrameLayout
import androidx.media3.ui.PlayerView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.PlaybackException
import androidx.media3.common.util.Log
import coil.compose.rememberAsyncImagePainter
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

    if (item.isImage) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(PitchBlack)
        ) {
            // 显示图片
            Image(
                painter = rememberAsyncImagePainter(item.url),
                contentDescription = null,
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Fit
            )

            // 显示浮动UI
            VideoFloatingUI(
                modifier = Modifier.fillMaxSize(),
                item = item,
                isDragging = false,
                isPlaying = false
            )
        }
        return
    }

    var isPlaying by remember { mutableStateOf(false) }
    var currentPosition by remember { mutableLongStateOf(0L) }
    var duration by remember { mutableLongStateOf(0L) }
    var isDragging by remember { mutableStateOf(false) }
    var videoAspectRatio by remember { mutableFloatStateOf(1f) }

    // 获取播放器实例
    val exoPlayer = remember { videoPlayer.getPlayer() ?: videoPlayer.initPlayer(item.url) }

    // 每次切换视频时更新 MediaItem
    LaunchedEffect(item.id) {
        PerformanceMonitor.reset()
        Log.d("VideoPerformance", "切换到视频: ${item.id}, URL: ${item.url}")
        exoPlayer.setMediaItem(MediaItem.fromUri(item.url))
        exoPlayer.prepare()
    }

    LaunchedEffect(isCurrentPage) {
        if (isCurrentPage) {
            Log.d("VideoPerformance", "视频开始播放: ${item.id}")
            exoPlayer.play()  // 当前页面才播放
        } else {
            Log.d("VideoPerformance", "视频暂停: ${item.id}")
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
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        Log.d("VideoPerformance", "视频进入缓冲状态: ${item.id}")
                    }
                    Player.STATE_READY -> {
                        Log.d("VideoPerformance", "视频准备就绪: ${item.id}")
                    }
                    Player.STATE_ENDED -> {
                        Log.d("VideoPerformance", "视频播放结束: ${item.id}")
                    }

                    Player.STATE_IDLE -> {
                        TODO()
                    }
                }
            }

            override fun onVideoSizeChanged(videoSize: androidx.media3.common.VideoSize) {
                videoAspectRatio = videoSize.width.toFloat() / videoSize.height.toFloat()
                Log.d("VideoPerformance", "视频尺寸变化: ${item.id}, 宽度=${videoSize.width}, 高度=${videoSize.height}")
            }

            override fun onIsPlayingChanged(playing: Boolean) {
                isPlaying = playing
                Log.d("VideoPerformance", "视频播放状态变化: ${item.id}, 正在播放=$playing")
            }
            override fun onPlayerError(error: PlaybackException) {
                Log.e("VideoPerformance", "视频播放错误: ${item.id}, 错误=${error.message}")
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
