package com.example.toutiaovideoflow.ui.video

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.toutiaovideoflow.ui.video.components.VideoPlayerPage
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun VideoScreen(vm: VideoViewModel = viewModel()) {
    val list by vm.videoList.collectAsState()

    VerticalPager(
        count = list.size,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        VideoPlayerPage(item = list[index])
    }
}
