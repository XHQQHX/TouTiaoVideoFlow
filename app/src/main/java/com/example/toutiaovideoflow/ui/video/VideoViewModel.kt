package com.example.toutiaovideoflow.ui.video

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.toutiaovideoflow.data.model.VideoItem
import com.example.toutiaovideoflow.data.repository.VideoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VideoViewModel : ViewModel() {

    private val repo = VideoRepository()

    private val _videoList = MutableStateFlow<List<VideoItem>>(emptyList())
    val videoList: StateFlow<List<VideoItem>> = _videoList

    init {
        loadNextPage()
    }

    fun loadNextPage(page: Int = 1) {
        viewModelScope.launch {
            val data = repo.loadPage(page)
            _videoList.value = data
        }
    }
}
