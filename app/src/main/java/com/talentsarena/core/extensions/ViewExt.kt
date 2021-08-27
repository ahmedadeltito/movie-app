package com.talentsarena.core.extensions

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.view.View

/**
 * Set the View visibility to VISIBLE and eventually animate the View alpha till 100%
 */
fun View.visible(animate: Boolean = true) {
    if (animate) {
        animate().alpha(1f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationStart(animation: Animator) {
                super.onAnimationStart(animation)
                visibility = View.VISIBLE
            }
        })
    } else {
        visibility = View.VISIBLE
    }
}

/**
 * Set the View visibility to INVISIBLE and eventually animate view alpha till 0%
 */
fun View.invisible(animate: Boolean = true) {
    hide(View.INVISIBLE, animate)
}

/**
 * Set the View visibility to GONE and eventually animate view alpha till 0%
 */
fun View.gone(animate: Boolean = true) {
    hide(View.GONE, animate)
}

/**
 * Convenient method that chooses between View.visible() or View.invisible() methods
 */
fun View.visibleOrInvisible(show: Boolean, animate: Boolean = true) {
    if (show) visible(animate) else invisible(animate)
}

/**
 * Convenient method that chooses between View.visible() or View.gone() methods
 */
fun View.visibleOrGone(show: Boolean, animate: Boolean = true) {
    if (show) visible(animate) else gone(animate)
}

/**
 * Set the View visibility to GONE or VISIBLE and eventually animate view alpha till 0%.
 */
private fun View.hide(hidingStrategy: Int, animate: Boolean = true) {
    if (animate) {
        animate().alpha(0f).setDuration(300).setListener(object : AnimatorListenerAdapter() {
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                visibility = hidingStrategy
            }
        })
    } else {
        visibility = hidingStrategy
    }
}