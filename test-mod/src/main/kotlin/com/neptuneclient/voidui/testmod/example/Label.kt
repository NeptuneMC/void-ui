package com.neptuneclient.voidui.testmod.example

import com.neptuneclient.voidui.ui.Drawable
import com.neptuneclient.voidui.ui.ReactiveComponent
import com.neptuneclient.voidui.ui.State
import com.neptuneclient.voidui.ui.elements.TestElement

class Label : ReactiveComponent() {

    @State
    private var counter = 0

    override fun build(): Drawable {
        return TestElement()
    }

}