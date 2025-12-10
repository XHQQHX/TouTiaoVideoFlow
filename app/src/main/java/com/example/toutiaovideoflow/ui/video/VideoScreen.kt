package com.example.toutiaovideoflow.ui.video

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.toutiaovideoflow.player.VideoPlayer
import com.example.toutiaovideoflow.ui.video.components.VideoPlayerPage

@Composable
fun VideoScreen(vm: VideoViewModel = viewModel()) {
    val list by remember { derivedStateOf { vm.videoList } }

    val pagerState = rememberPagerState(pageCount = { list.size })
    val context = LocalContext.current

    // 为每个页面创建独立播放器
    val videoPlayers = remember { mutableMapOf<Int, VideoPlayer>() }

    // 分页加载逻辑
    LaunchedEffect(pagerState.currentPage, list.size) {
        if (pagerState.currentPage >= list.size - 2) {
            vm.loadNextPage()
        }
    }

    // 当页面切换时，释放不在当前页面的播放器，优化启播速度
    LaunchedEffect(pagerState.currentPage) {
        val currentPage = pagerState.currentPage
        val pagesToKeep = setOf(currentPage - 1, currentPage, currentPage + 1)

        videoPlayers.keys.toList().forEach { page ->
            if (page !in pagesToKeep) {
                videoPlayers[page]?.release()
                videoPlayers.remove(page)
            }
        }
    }


    VerticalPager(
        state = pagerState,
        modifier = Modifier.fillMaxSize()
    ) { page ->
        val item = list[page]

        // 获取或创建当前页面播放器
        val videoPlayer = videoPlayers.getOrPut(page) { VideoPlayer(context) }
        val isCurrentPage = pagerState.currentPage == page

        VideoPlayerPage(
            item = item,
            videoPlayer = videoPlayer,
            isCurrentPage
        )

    }
}
