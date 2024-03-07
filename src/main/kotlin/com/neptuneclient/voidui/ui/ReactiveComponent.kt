package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.event.EventManager
import com.neptuneclient.voidui.event.Subscribe
import com.neptuneclient.voidui.event.impl.StateChangeEvent

abstract class ReactiveComponent : Component() {
    val states = mutableListOf<State<*>>()

    init {
        EventManager.instance.register(this)
        for (field in this.javaClass.declaredFields) {
            if (field.type == State::class.java) {
                field.isAccessible = true
                val state = field.get(this) as State<*>
                state.init()
                states.add(state)
                state.subscribe { newValue ->
                    // Here you can react to changes in the state.
                    // For example, you can rebuild the component.
                    rebuild()
                }
            }
        }
    }

    override fun build(): Component {
        return this
    }

    open fun rebuild() {
        // This method is called when a state changes.
        // You can override it to rebuild the component.
    }

    @Subscribe(target = StateChangeEvent::class)
    fun onStateChange(event: StateChangeEvent<*>) {
        println("State changed from ${event.state.prev} to ${event.state.value}")
    }
}