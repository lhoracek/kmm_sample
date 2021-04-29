package com.jetbrains.kmm.shared

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual class Platform actual constructor() {
    actual val platform: String = "Android ${android.os.Build.VERSION.SDK_INT}"
}

internal actual fun mainDispatcher(): CoroutineDispatcher = Dispatchers.Main
