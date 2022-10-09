package com.example.android_kotlin.workManager.periodicWorkRequest

enum class WorkState(var state : String) {
    ENQUEUED("ENQUEUED"),
    STARTED("STARTED"),
    FINISHED("FINISHED")
}