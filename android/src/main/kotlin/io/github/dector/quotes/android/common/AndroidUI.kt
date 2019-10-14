package io.github.dector.quotes.android.common

import android.app.Activity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.SupervisorJob

fun Activity.fullscreen() {
    window.decorView.systemUiVisibility = android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY or
            android.view.View.SYSTEM_UI_FLAG_FULLSCREEN or
            android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
}

fun UIScope(): CoroutineScope = MainScope()
fun IOScope(): CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
