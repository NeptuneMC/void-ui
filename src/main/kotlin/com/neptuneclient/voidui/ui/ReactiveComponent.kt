package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.event.EventManager
import com.neptuneclient.voidui.event.Subscribe
import com.neptuneclient.voidui.event.impl.StateChangeEvent

abstract class ReactiveComponent : Component() {
    // array of states
    var state: HashMap<String, State<Any>> = hashMapOf()
    // call function on state change
    open fun init() {
        EventManager.instance.register(this)

        for (s in state) {
            s.value.init()
        }
    }
    override fun build(): Component {
        return this
    }

    @Subscribe(target = StateChangeEvent::class)
    fun onStateChange(event: StateChangeEvent<*>) {
        println("State changed from ${event.state.prev} to ${event.state.value}")
    }
}