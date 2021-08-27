package com.talentsarena.core.navigation

import androidx.fragment.app.Fragment

/**
 * Extension function to load intent from fragment string path
 */
internal fun String.loadFragmentOrNull(): Fragment? = try {
    this.loadClassOrNull<Fragment>()?.newInstance()
} catch (e: ClassNotFoundException) {
    null
}
