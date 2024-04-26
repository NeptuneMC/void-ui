@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.MouseClickedEvent
import com.neptuneclient.voidui.event.MouseReleasedEvent
import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Screen
import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.Border
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.objects.EdgeInsets
import com.neptuneclient.voidui.widgets.*
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW
import java.awt.Color

fun EpicButton(label: String): Widget {
    return Container(
        color = Color(140, 60, 255),
        cornerRadius = CornerRadius.all(10f),
        border = Border(1f, Color(255, 255, 255, 100)),

        child = Padding(
            padding = EdgeInsets.symmetric(10f, 50f),
            child = Text(label)
        )
    )
}

class TestScreen(voidUI: VoidUI) : Screen(voidUI) {

    override fun build(): Widget {
        return Positioned(
            position = Offset(300f, 300f),
            child = Column(
                gap = 20f,
                children = arrayOf(
                    EpicButton("Singleplayer"),
                    EpicButton("Multiplayer")
                )
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

    screen.remove()
    voidUI.destroy()
}

fun mouseEvent(button: Int, action: Int, x: Float, y: Float) {
    if (action == GLFW.GLFW_PRESS)
        MouseClickedEvent(button, x, y).call(voidUI)
    else
        MouseReleasedEvent(button, x, y).call(voidUI)
}