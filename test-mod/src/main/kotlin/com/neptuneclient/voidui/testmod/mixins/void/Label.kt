package com.neptuneclient.voidui.testmod.mixins.void

import com.neptuneclient.voidui.ui.Component
import com.neptuneclient.voidui.ui.ReactiveComponent
import com.neptuneclient.voidui.ui.State

class Label: ReactiveComponent() {
    override fun init() {
        super.init()

        state = hashMapOf(
            "number" to State(0)
        )
    }
    override fun build(): Component {
        // wait 4 seconds
        Thread(Runnable {
            Thread.sleep(4000)
            this.state["number"]?.value = 1
        })
        return super.build()
    }
}