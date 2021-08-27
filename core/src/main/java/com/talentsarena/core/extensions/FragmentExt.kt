package com.talentsarena.core.extensions

import androidx.annotation.AnimRes
import androidx.annotation.IdRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction

private inline fun FragmentManager.inTransaction(crossinline func: FragmentTransaction.() -> Unit) {
    beginTransaction().func()
}

fun AppCompatActivity.replaceFragment(
    fragment: Fragment,
    @IdRes containerViewId: Int,
    fragmentTransition: Int? = 0,
    @AnimRes enterAnimation: Int = 0,
    @AnimRes exitAnimation: Int = 0,
    @AnimRes popEnterAnimation: Int = 0,
    @AnimRes popExitAnimation: Int = 0
) {
    supportFragmentManager.inTransaction {
        fragmentTransition?.let {
            setTransition(it)
        }
        setCustomAnimations(
            enterAnimation,
            exitAnimation,
            popEnterAnimation,
            popExitAnimation
        )
        replace(containerViewId, fragment, fragment::class.java.simpleName)
        addToBackStack(fragment::class.java.simpleName)
        if (!supportFragmentManager.isStateSaved) {
            commit()
        } else {
            commitAllowingStateLoss()
        }
    }
}