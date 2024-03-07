package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.event.EventManager
import com.neptuneclient.voidui.event.impl.StateChangeEvent

class State<T : Any>(initial: T) {
    var value: T = initial
        set(value) {
            field = value
            changed()
        }

    var prev: T = initial
    private val listeners = mutableListOf<(T) -> Unit>()

    fun init() {
        EventManager.instance.register(this)
        println("State initialized")
    }

    fun changed() {
        EventManager.instance.fire(
            StateChangeEvent(this)
        )
        prev = this.value
        listeners.forEach { it(value) } // Notify all listeners about the change
    }

    fun subscribe(listener: (T) -> Unit) {
        listeners.add(listener)
    }
}