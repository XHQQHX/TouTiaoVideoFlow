package com.example.toutiaovideoflow.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class VideoPlayer(private val context: Context) {

    private var exoPlayer: ExoPlayer? = null

    fun initPlayer(url: String): ExoPlayer {
        val player = ExoPlayer.Builder(context).build()
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.prepare()
        exoPlayer = player
        return player
    }

    fun getPlayer(): ExoPlayer? = exoPlayer

    fun release() {
        exoPlayer?.release()
        exoPlayer = null
    }

    fun getCurrentPosition(): Long = exoPlayer?.currentPosition ?: 0L
    fun getDuration(): Long = exoPlayer?.duration ?: 0L
    fun seekTo(position: Long) {
        exoPlayer?.seekTo(position)
    }
}
