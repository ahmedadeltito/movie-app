package com.talentsarena.core.extensions

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager

/**
 * Helper function to return the fragment tag from the [FragmentManager]
 */
fun AppCompatActivity.getFragmentTag(): String? {
    val index = supportFragmentManager.backStackEntryCount - 1
    val backEntry: FragmentManager.BackStackEntry =
        supportFragmentManager.getBackStackEntryAt(index)
    return backEntry.name
}