package com.talentsarena.core.extensions

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * A timer function implemented with a Coroutine Flavor
 */
@DelicateCoroutinesApi
inline fun startCoroutineTimer(
    delayMillis: Long = 0, repeatMillis: Long = 0, crossinline action: () -> Unit,
): Job = GlobalScope.launch {
    delay(delayMillis)
    if (repeatMillis > 0) {
        while (true) {
            action()
            delay(repeatMillis)
        }
    } else {
        action()
    }
}