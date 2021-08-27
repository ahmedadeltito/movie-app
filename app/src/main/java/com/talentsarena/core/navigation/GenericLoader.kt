package com.talentsarena.core.navigation

private val classMap = mutableMapOf<String, Class<*>>()

private inline fun <reified T : Any> Any.castOrNull() = this as? T

/**
 * Checks if the loading class already exists in the project or not. This is a good way for pre building
 * dynamic features.
 */
internal fun <T> String.loadClassOrNull(): Class<out T>? = classMap.getOrPut(this) {
    try {
        Class.forName(this)
    } catch (e: ClassNotFoundException) {
        return null
    }
}.castOrNull()