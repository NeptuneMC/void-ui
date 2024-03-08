package com.neptuneclient.voidui

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.ui.Component
import com.neptuneclient.voidui.ui.ReactiveComponent

/**
 * The main class of VoidUI. It is mostly used to contain settings and singleton instances of other classes
 * like the renderer in it. It has to be passed whenever creating a screen, however it does not handle any of
 * the screen displaying logic, since this is supposed to be handled through Minecraft.
 */
class VoidUI
/**
 * @param renderer The renderer used to draw all components in the screens. Void does not come with a renderer implementation
 * out of the box so the user has to create his own implementation.
 */
constructor(val renderer: Renderer) {

}

class TestComponent : ReactiveComponent() {

    var testState by state(2)

    override fun build(): Component {
        println("Building TestComponent")
        return this
    }
}

fun main(args: Array<String>) {

    val component = TestComponent().build()
    if (component is TestComponent) {
        println("TestComponent.testState: ${component.testState}")
        component.testState = 1
        println("TestComponent.testState: ${component.testState}")
    }

}