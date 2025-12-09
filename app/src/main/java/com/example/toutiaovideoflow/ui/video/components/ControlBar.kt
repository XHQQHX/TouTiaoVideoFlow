package com.example.toutiaovideoflow.ui.video.components
import android.app.Activity
import android.content.pm.ActivityInfo
import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import com.example.toutiaovideoflow.R

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toutiaovideoflow.ui.theme.*
//控制栏
@Composable
fun ControlBar(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    currentPosition: Long,
    duration: Long,
    videoAspectRatio: Float,
    onSeek: (Long) -> Unit = {},
    onPlayPause: () -> Unit = {},
    onDraggingChanged: (Boolean) -> Unit = {}
) {
    val configuration = LocalConfiguration.current
    when (configuration.orientation) {
        Configuration.ORIENTATION_LANDSCAPE -> {
            LandscapeControlBar(
                modifier = modifier,
                isPlaying = isPlaying,
                currentPosition = currentPosition,
                duration = duration,
                videoAspectRatio = videoAspectRatio,
                onSeek = onSeek,
                onPlayPause = onPlayPause,
                onDraggingChanged = onDraggingChanged
            )
        }
        else -> {
            PortraitControlBar(
                modifier = modifier,
                isPlaying = isPlaying,
                currentPosition = currentPosition,
                duration = duration,
                videoAspectRatio = videoAspectRatio,
                onSeek = onSeek,
                onPlayPause = onPlayPause,
                onDraggingChanged = onDraggingChanged
            )
        }
    }
}

@Composable
private fun LandscapeControlBar(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    currentPosition: Long,
    duration: Long,
    videoAspectRatio: Float,
    onSeek: (Long) -> Unit,
    onPlayPause: () -> Unit,
    onDraggingChanged: (Boolean) -> Unit
) {
    // 横屏模式的UI实现
    Box(modifier = modifier.fillMaxSize()) {
        val context = LocalContext.current
        val activity = context as Activity

        // 播放/暂停按钮
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onPlayPause() }
        )

        if (!isPlaying) {
            // 顶部栏
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
                    contentDescription = "返回",
                    tint = White,
                    modifier = Modifier
                        .size(35.dp)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ) {
                            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
                        }
                )
                Text(
                    text = "清晰度",
                    color = White,
                    fontSize = 20.sp
                )
            }

            // 播放/暂停图标
            Icon(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = "暂停",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                tint = WhiteTransparent
            )

            // 进度条
            VideoSlider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .padding(bottom = 60.dp)
                    .align(Alignment.BottomCenter),
                currentPosition = currentPosition,
                duration = duration,
                onSeek = onSeek,
                onDraggingChanged = onDraggingChanged
            )
        }
    }
}
@Composable
private fun PortraitControlBar(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    currentPosition: Long,
    duration: Long,
    videoAspectRatio: Float,
    onSeek: (Long) -> Unit,
    onPlayPause: () -> Unit,
    onDraggingChanged: (Boolean) -> Unit
) {
    // 竖屏模式的UI实现
    Box(modifier = modifier.fillMaxSize()) {
        val context = LocalContext.current
        val activity = context as Activity

        // 播放/暂停按钮
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 60.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) { onPlayPause() }
        )

        // 顶部栏
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "返回",
                tint = White,
                modifier = Modifier
                    .size(25.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        activity.finish()
                    }
            )
            Text(
                text = "清晰度",
                color = White,
                fontSize = 16.sp
            )
        }

        // 播放/暂停图标
        if (!isPlaying) {
            Icon(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = "暂停",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                tint = WhiteTransparent
            )
        }

        // 全屏按钮
        if (videoAspectRatio > 1) {
            Box(
                modifier = Modifier
                    .offset(y = 180.dp)
                    .background(
                        color = Black.copy(alpha = 0.2f),
                        shape = RoundedCornerShape(20.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 10.dp)
                    .align(Alignment.Center)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    ) {
                        activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                    }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_rotate),
                        contentDescription = "旋转",
                        modifier = Modifier.size(18.dp),
                        tint = White
                    )
                    Text(
                        text = "全屏观看",
                        color = White,
                        fontSize = 13.sp
                    )
                }
            }
        }

        // 进度条
        VideoSlider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .align(Alignment.BottomCenter),
            currentPosition = currentPosition,
            duration = duration,
            onSeek = onSeek,
            onDraggingChanged = onDraggingChanged
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun VideoSlider(
    modifier: Modifier = Modifier,
    currentPosition: Long,
    duration: Long,
    onSeek: (Long) -> Unit,
    onDraggingChanged: (Boolean) -> Unit
) {
    var isDragging by remember { mutableStateOf(false) }
    var dragPosition by remember { mutableFloatStateOf(0f) }

    Slider(
        modifier = modifier,
        value = if (isDragging) {
            dragPosition
        } else {
            if (duration > 0) currentPosition.toFloat() / duration else 0f
        },
        onValueChange = { value ->
            dragPosition = value
            if (!isDragging) {
                isDragging = true
                onDraggingChanged(true)
            }
            onSeek((value * duration).toLong())
        },
        onValueChangeFinished = {
            isDragging = false
            onDraggingChanged(false)
            onSeek((dragPosition * duration).toLong())
        },
        colors = SliderDefaults.colors(
            thumbColor = White,
            activeTrackColor = Silver,
            inactiveTrackColor = Charcoal,
        ),
        valueRange = 0f..1f,
        thumb = {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .background(
                        color = White,
                        shape = CircleShape
                    )
            )
        },
        track = { sliderState ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(
                        color = Charcoal,
                        shape = RoundedCornerShape(2.dp)
                    )
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxHeight()
                        .fillMaxWidth(sliderState.value)
                        .background(
                            color = Silver,
                            shape = RoundedCornerShape(2.dp)
                        )
                )
            }
        }
    )
}

