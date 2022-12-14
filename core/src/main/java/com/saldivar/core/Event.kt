package com.saldivar.core

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 * Created by César Jeanpierre Saldivar on 25/11/2022.
 * @devjeanpierre
 * Lima, Peru.
 **/
open class Event<out T>(private val content: T) {

    private var hasBeenHandled = false
        private set // Allow external read but not write

    /**
     * Returns the content and prevents its use again.
     */
    fun getContentIfNotHandled(): T? {
        return if (hasBeenHandled) {
            null
        } else {
            hasBeenHandled = true
            content
        }
    }

    /**
     * Returns the content, even if it's already been handled.
     */
    fun peekContent(): T = content

    override fun equals(other: Any?): Boolean {
        return if (other is Event<*>) {
            other.content == this.content
        } else {
            false
        }
    }

    override fun hashCode(): Int {
        return content.hashCode()
    }

    override fun toString(): String {
        return content.toString()
    }
}

/**
 * An [Observer] for [Event]s, simplifying the pattern of checking if the [Event]'s content has
 * already been handled.
 *
 * [onEventUnhandledContent] is *only* called if the [Event]'s contents has not been handled.
 */
inline fun <T> LiveData<Event<T>>.observeEvent(
    owner: LifecycleOwner,
    crossinline onEventUnhandledContent: (T) -> Unit
) {
    observe(owner) { it?.getContentIfNotHandled()?.let(onEventUnhandledContent) }
}