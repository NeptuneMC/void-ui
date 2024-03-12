package com.neptuneclient.voidui.testmod.example

import com.neptuneclient.voidui.ui.Drawable
import com.neptuneclient.voidui.ui.ReactiveComponent
import com.neptuneclient.voidui.ui.elements.TestElement

class Label(text: String) : ReactiveComponent() {

    private var counter by state(0)

    override fun build(): Drawable {
        return TestElement()
    }

}