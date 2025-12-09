package com.example.toutiaovideoflow.player

import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer

class VideoPlayer(private val context: Context) {

    private var exoPlayer: ExoPlayer? = null

    fun initPlayer(url: String): ExoPlayer {
        PerformanceMonitor.startVideoLoad(url)
        val player = ExoPlayer.Builder(context).build()
        val mediaItem = MediaItem.fromUri(url)
        player.setMediaItem(mediaItem)
        player.addListener(object : Player.Listener {
            override fun onPlaybackStateChanged(playbackState: Int) {
                when (playbackState) {
                    Player.STATE_BUFFERING -> {
                        PerformanceMonitor.startBuffering()
                    }
                    Player.STATE_READY -> {
                        PerformanceMonitor.endBuffering()
                        PerformanceMonitor.onVideoReady(url)
                    }
                    Player.STATE_ENDED -> {
                        PerformanceMonitor.onVideoCompleted(url)
                    }

                    Player.STATE_IDLE -> {
                        TODO()
                    }
                }
            }

            override fun onPlayerError(error: PlaybackException) {
                PerformanceMonitor.logPlaybackError(error.message ?: "未知错误", url)
            }
        })
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
