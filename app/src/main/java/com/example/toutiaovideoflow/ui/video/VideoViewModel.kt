package com.example.toutiaovideoflow.ui.video

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoViewModel : ViewModel() {

    private val repo = VideoRepository()

    var cursor by mutableStateOf<Int?>(0)
    var videoList by mutableStateOf(listOf<VideoItem>())
    var isLoading by mutableStateOf(false)

    init {
        loadFirstPage()
    }

    fun loadFirstPage() {
        val (list, next) = repo.loadPage(0, limit = 5)
        videoList = list
        cursor = next
    }

    fun loadNextPage() {
        val nextCursor = cursor ?: return
        if (isLoading) return
        isLoading = true

        viewModelScope.launch {
            val (list, newCursor) = repo.loadPage(nextCursor)
            videoList = videoList + list
            cursor = newCursor
            isLoading = false
        }
    }
}
