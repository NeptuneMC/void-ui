@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.MouseClickedEvent
import com.neptuneclient.voidui.event.MouseReleasedEvent
import com.neptuneclient.voidui.widgets.*
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW

class PaddingPanel(private val padding: EdgeInsets = EdgeInsets.zero, private val child: Widget) : Widget() {

    override fun build(): Widget {
        return Panel(
            Padding(
                child,
                padding
            )
        )
    }

}

class TestScreen(void: VoidUI) : Screen(void) {

    private var counter by state(5)

    override fun build(): Widget {
        return Panel(
            child = BackgroundPanel(
                child = Text("Hello World!"),
            )
        )
    }

}

val voidUI = VoidUI(TestRenderer(), TestTheme())

fun main() {
    val screen = TestScreen(voidUI)
    screen.init()

    val renderer = voidUI.renderer as TestRenderer
    while (!GLFW.glfwWindowShouldClose(renderer.window)) {
        screen.render()
    }

    renderer.destroy()
}

fun mouseEvent(button: Int, action: Int, x: Float, y: Float) {
    if (action == GLFW.GLFW_PRESS)
        MouseClickedEvent(button, x, y).call(voidUI)
    else
        MouseReleasedEvent(button, x, y).call(voidUI)
}