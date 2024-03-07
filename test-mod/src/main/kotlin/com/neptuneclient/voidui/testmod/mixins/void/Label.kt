package com.neptuneclient.voidui.testmod.mixins.void

import com.neptuneclient.voidui.ui.ReactiveComponent
import com.neptuneclient.voidui.ui.State

class Label(initialText: String) : ReactiveComponent() {
    val text = State(initialText)

    init {
        text.subscribe {
            rebuild()
        }
    }

    override fun rebuild() {
        // Logic to update the UI goes here.
        // This might involve re-rendering the component's UI, updating its data, etc.
        println("Current text: ${text.value}")
    }
}