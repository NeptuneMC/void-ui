@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.MouseClickedEvent
import com.neptuneclient.voidui.event.MouseReleasedEvent
import com.neptuneclient.voidui.framework.Screen
import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.Border
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.objects.EdgeInsets
import com.neptuneclient.voidui.widgets.*
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW
import java.awt.Color

class TestScreen(voidUI: VoidUI) : Screen(voidUI) {

    override fun build(): Widget {
        return Container(
            margin = EdgeInsets.all(100f),
            padding = EdgeInsets.all(30f),
            cornerRadius = CornerRadius.all(10f),
            border = Border(2f, Color.CYAN),
            color = Color.RED,
            child = Text("Hello World!"),
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

    screen.remove()
    voidUI.destroy()
}

fun mouseEvent(button: Int, action: Int, x: Float, y: Float) {
    if (action == GLFW.GLFW_PRESS)
        MouseClickedEvent(button, x, y).call(voidUI)
    else
        MouseReleasedEvent(button, x, y).call(voidUI)
}