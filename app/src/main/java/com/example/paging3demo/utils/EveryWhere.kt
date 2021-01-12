package com.example.paging3demo.utils

import android.util.Log
import com.example.paging3demo.BuildConfig

/**
 * Created by OpenYourEyes on 10/22/2020
 */

inline fun printLog(message: Any?) {
    if (message == null || !BuildConfig.DEBUG) return
    val stackTraceElement = Thread.currentThread().stackTrace[4]
    Log.d(
        "[DKS - ${stackTraceElement.fileName}->${stackTraceElement.methodName}->${stackTraceElement.lineNumber}]",
        "#$message"
    )
}