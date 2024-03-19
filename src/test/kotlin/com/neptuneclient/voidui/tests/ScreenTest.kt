@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.ui.Group
import com.neptuneclient.voidui.ui.Panel
import com.neptuneclient.voidui.ui.Screen
import com.neptuneclient.voidui.ui.Widget
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW

class TestScreen(void: VoidUI) : Screen(void) {

    override fun build(): Widget {
        return Group(
            children = arrayOf(
                Panel(),
                Panel(),
                Panel(),
                Panel()
            )
        )
    }

}

val voidUI = VoidUI(TestRenderer())

fun main() {
    val screen = TestScreen(voidUI)
    screen.init()
    
    val renderer = voidUI.renderer as TestRenderer
    while (!GLFW.glfwWindowShouldClose(renderer.window)) {
        screen.render()
    }
    
    renderer.destroy()
}