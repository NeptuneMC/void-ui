@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.widgets.*
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW

class TestScreen(void: VoidUI) : Screen(void) {

    private var counter by state(5)

    override fun build(): Widget {
        return BackgroundPanel(
            child = Padding(
                padding = EdgeInsets.all(10F),
                child = Column(
                    gap = 10,   // 10 pixels
                    children = arrayOf(
                        Text("Hello"),
                        Text("World")
                    )
                )
            )
        )
    }

}

val voidUI = VoidUI(TestRenderer(), TestTheme())

fun main() {
    val screen = TestScreen(voidUI)
    screen.init()
    screen.layout()
    screen.recalcOffsets()

    val renderer = voidUI.renderer as TestRenderer
    while (!GLFW.glfwWindowShouldClose(renderer.window)) {
        screen.render()
    }
    
    renderer.destroy()
}