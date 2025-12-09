import android.util.Log

// 在PerformanceMonitor.kt中添加基于日志的监控功能
object PerformanceMonitor {
    private const val TAG = "VideoPerformance"

    // 记录视频起播开始时间
    private var videoLoadStartTime = 0L
    private var bufferingStartTime = 0L
    private var totalBufferingTime = 0L
    private var isInitialBuffer = true

    // 开始加载视频
    fun startVideoLoad(videoUrl: String) {
        videoLoadStartTime = System.currentTimeMillis()
        Log.d(TAG, "开始加载视频: $videoUrl, 时间戳: $videoLoadStartTime")
    }

    // 视频准备就绪（可以开始播放）
    fun onVideoReady(videoUrl: String) {
        val loadTime = System.currentTimeMillis() - videoLoadStartTime
        Log.d(TAG, "视频准备就绪: $videoUrl, 起播耗时: ${loadTime}ms")

        // 记录关键指标
        Log.i(TAG, "METRIC: 起播时间=${loadTime}ms, 视频URL=${videoUrl}")
    }

    // 开始缓冲
    fun startBuffering() {
        bufferingStartTime = System.currentTimeMillis()
        Log.d(TAG, "开始缓冲, 时间戳: $bufferingStartTime")
    }

    // 缓冲结束
    fun endBuffering() {
        val bufferingDuration = System.currentTimeMillis() - bufferingStartTime
        totalBufferingTime += bufferingDuration

        Log.d(TAG, "缓冲结束, 耗时: ${bufferingDuration}ms")

        if (isInitialBuffer) {
            isInitialBuffer = false
            Log.i(TAG, "METRIC: 首次缓冲时间=${bufferingDuration}ms")
        } else {
            Log.i(TAG, "METRIC: 缓冲时间=${bufferingDuration}ms")
        }
    }

    // 记录播放错误
    fun logPlaybackError(error: String, videoUrl: String) {
        Log.e(TAG, "播放错误: $error, 视频URL: $videoUrl")
        Log.i(TAG, "METRIC: 播放错误=1, 错误类型=${error}")
    }

    // 记录带宽信息
    fun logBandwidth(bps: Long) {
        Log.d(TAG, "当前带宽估计: ${bps/1024}Kbps")
        Log.i(TAG, "METRIC: 带宽=${bps}bps")
    }

    // 记录丢帧情况
    fun logDroppedFrames(count: Int) {
        Log.d(TAG, "丢帧数量: $count")
        Log.i(TAG, "METRIC: 丢帧数=${count}")
    }

    // 记录视频播放完成
    fun onVideoCompleted(videoUrl: String) {
        Log.d(TAG, "视频播放完成: $videoUrl")
        Log.i(TAG, "METRIC: 总缓冲时间=${totalBufferingTime}ms")
    }

    // 重置监控状态
    fun reset() {
        videoLoadStartTime = 0L
        bufferingStartTime = 0L
        totalBufferingTime = 0L
        isInitialBuffer = true
    }
}
