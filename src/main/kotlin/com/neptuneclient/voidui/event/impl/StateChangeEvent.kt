package com.neptuneclient.voidui.event.impl

import com.neptuneclient.voidui.event.Event
import com.neptuneclient.voidui.ui.State

class StateChangeEvent<T : Any>(state: State<T>) : Event() {
    var state: State<T> = state
}