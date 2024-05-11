@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.Settings
import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.MouseClickedEvent
import com.neptuneclient.voidui.event.MouseReleasedEvent
import com.neptuneclient.voidui.framework.Screen
import com.neptuneclient.voidui.framework.Template
import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.Border
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.objects.EdgeInsets
import com.neptuneclient.voidui.objects.TextAlign
import com.neptuneclient.voidui.utils.image
import com.neptuneclient.voidui.widgets.*
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW
import java.awt.Color

fun EpicButton(label: String): Widget {
    return Container(
        color = Color(69, 99, 255),
        cornerRadius = CornerRadius.all(10f),
        border = Border(1f, Color(255, 255, 255, 100)),
        width = 240f,
        height = 40f,

        child = Align(
            alignment = Alignment.center,
            child = Text(label)
        )
    )
}

fun Menu(): Widget {
    return Container(
        color = Color(14, 15, 20),
        cornerRadius = CornerRadius.all(10f),
        padding = EdgeInsets.all(20f),

        child = Column(
            gap = 20f,
            crossAxisAlignment = CrossAxisAlignment.STRETCH,
            children = arrayOf(
                Container(
                    color = Color.RED,
                    child = Text(
                        label = "Neptune popo main menu",
                        align = TextAlign.CENTER
                    )
                ),
                EpicButton("Singleplayer"),
                EpicButton("Multiplayer")
            )
        )
    )
}

class TestScreen(voidUI: VoidUI) : Screen(voidUI) {

    override fun build(): Widget {
        return Container(
            margin = EdgeInsets.all(10f),
            child = Image(
                src = image("images/hampter.png"),
                fit = ImageFit.COVER,
                cornerRadius = CornerRadius.all(10f)
            )
        )
    }

}

private val template = Template { slot ->
    Stack(
        children = arrayOf(
            Image(
                src = image("images/hampter2.png"),
                fit = ImageFit.STRETCH
            ),
            Center(slot)
        )
    )
}
val voidUI = VoidUI(TestRenderer(), TestTheme(), Settings(centeredScreen = false), /*template*/)

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