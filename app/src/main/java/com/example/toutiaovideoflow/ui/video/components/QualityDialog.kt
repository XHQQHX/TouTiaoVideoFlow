package com.example.toutiaovideoflow.ui.video.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.*
import androidx.compose.runtime.*

@Composable
fun QualityDialog(
    show: Boolean,
    onDismiss: () -> Unit,
    onSelect: (String) -> Unit
) {
    if (show) {
        AlertDialog(
            onDismissRequest = onDismiss,
            confirmButton = {},
            text = {
                Column {
                    Text("选择清晰度")
                    listOf("360P", "480P", "720P", "1080P").forEach { q ->
                        TextButton(onClick = { onSelect(q) }) {
                            Text(q)
                        }
                    }
                }
            }
        )
    }
}
