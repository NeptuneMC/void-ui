@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.widgets.*
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW

class TestScreen(void: VoidUI) : Screen(void) {

    override fun build(): Widget {
        return Row(
            children = arrayOf(
                Panel(),
                Text(label = "Hello World!")
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