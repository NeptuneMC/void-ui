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
                padding = EdgeInsets.all(20F),
                child = Column(
                    gap = 10,
                    children = arrayOf(
                        Text("Title"),
                        Text("Hello World!")
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

    val renderer = voidUI.renderer as TestRenderer
    while (!GLFW.glfwWindowShouldClose(renderer.window)) {
        screen.render()
    }
    
    renderer.destroy()
}