package com.example.toutiaovideoflow.utils

import android.util.Log

object PerformanceMonitor {
    private var startTime = 0L

    fun start() {
        startTime = System.currentTimeMillis()
    }

    fun end(tag: String = "Perf") {
        val cost = System.currentTimeMillis() - startTime
        Log.d(tag, "耗时：${cost}ms")
    }
}
