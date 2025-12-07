package com.example.toutiaovideoflow.ui.video.components
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import com.example.toutiaovideoflow.R

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.toutiaovideoflow.ui.theme.*
//顶部栏
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ControlBar(
    modifier: Modifier = Modifier,
    isPlaying: Boolean,
    currentPosition: Long,
    duration: Long,
    onSeek: (Long) -> Unit = {},
    onPlayPause: () -> Unit = {},
    onDraggingChanged: (Boolean) -> Unit = {}

) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clickable {onPlayPause()},
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "返回",
                modifier = Modifier.size(25.dp),
                tint = White
            )
            Text(
                text = "清晰度",
                color = White,
                fontSize = 16.sp
            )
        }
        if(!isPlaying)
        {
            Icon(
                painter = painterResource(id = R.drawable.ic_pause),
                contentDescription = "暂停",
                modifier = Modifier
                    .size(80.dp)
                    .align(Alignment.Center),
                tint = WhiteTransparent
            )
        }
        Slider(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .align(Alignment.BottomCenter),
            value = if (duration > 0) currentPosition.toFloat() / duration else 0f,
            onValueChange = { value ->
                onSeek((value * duration).toLong())
            },

            colors = SliderDefaults.colors(
                thumbColor = Color.Transparent,
                activeTrackColor = Color.Transparent,
                inactiveTrackColor = Color.Transparent,
            ),
            valueRange = 0f..1f,
            thumb = {
                Box(
                    modifier = Modifier
                        .size(6.dp)
                        .background(
                            color = White,
                            shape = CircleShape
                        )
                        .align(Alignment.Center)
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
}
@Preview
@Composable
fun ControlBarPreview() {
    ControlBar(
        isPlaying = true,
        currentPosition = 1000,
        duration = 60000
    )
}