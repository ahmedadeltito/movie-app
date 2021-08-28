package com.talentsarena.core.ui

import androidx.annotation.MainThread

/**
 * A common interface implemented by each UI component to accept rendering updates.
 *
 * @param T A rendering of type [T] that holds the necessary state and listeners needed by the UI component.
 */
fun interface Rendering<T> {

    /**
     * Renders the screen based on the current [renderingUpdate]. This method needs to be called on the main thread.
     *
     * @param renderingUpdate A transaction that produces a new rendering of type [T] based on the current one.
     */
    @MainThread
    fun render(renderingUpdate: (T) -> T)
}
