package com.talentsarena.core.navigation

import android.content.Intent

const val PACKAGE_NAME = "com.talentsarena"

/**
 * Extension function to load intent from activity string path
 */
inline fun String.loadIntentOrNull(crossinline func: Intent.() -> Intent): Intent? = try {
    Class.forName(this).run {
        Intent(Intent.ACTION_VIEW).apply {
            setClassName(PACKAGE_NAME, this@loadIntentOrNull)
            func()
        }
    }
} catch (e: ClassNotFoundException) {
    null
}
