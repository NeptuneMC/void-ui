package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.event.EventManager
import com.neptuneclient.voidui.event.impl.StateChangeEvent

class State <T : Any> (initial: T) {
    // make the setter like react
    var value: T = initial
        set(value) {
            field = value
            changed()
        }

    var prev: T = initial
    fun init() {
        EventManager.instance.register(this)
        println("State initialized")
    }

    fun changed() {
        EventManager.instance.fire(
            StateChangeEvent(this)
        )
        prev = this.value
    }
}