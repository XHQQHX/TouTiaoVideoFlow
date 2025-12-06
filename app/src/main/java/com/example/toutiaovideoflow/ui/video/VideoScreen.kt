package com.example.toutiaovideoflow.ui.video

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.toutiaovideoflow.ui.video.components.VideoPlayerPage
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState

@Composable
fun VideoScreen(vm: VideoViewModel = viewModel()) {
    val list by vm.videoList.collectAsState()

    val pagerState = rememberPagerState(pageCount = { list.size })

    // 监听页面变化
    LaunchedEffect(pagerState.currentPage) {
        // 页面切换时的逻辑
    }

    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        VideoPlayerPage(
            item = list[page],
        )
    }
}
