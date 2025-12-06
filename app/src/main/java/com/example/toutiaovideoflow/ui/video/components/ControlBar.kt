package com.example.toutiaovideoflow.ui.video.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ControlBar(
    isPlaying: Boolean,
    progress: Float,
    onPlayPause: () -> Unit,
    onSeek: (Float) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {

        Slider(
            value = progress,
            onValueChange = { onSeek(it) },
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Button(onClick = onPlayPause) {
                Text(if (isPlaying) "暂停" else "播放")
            }
        }
    }
}
