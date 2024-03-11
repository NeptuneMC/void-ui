package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.ui.elements.Text
import com.neptuneclient.voidui.utils.Font
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW
import java.awt.Color
import java.nio.file.Path

@DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")  // dang simon how do u know shit line this
class FontTest() {

    val void = VoidUI(TestRenderer())

    @Test
    fun byteBufferTest() {
        val font = Font(void, "test", Path.of("fonts/WorkSans-Regular.ttf"), 30)
        println(font.data.toString())

        val text = Text(
            x = 100,
            y = 100,
            text = "Hello World!",
            font = font,
            color = Color.WHITE
        )
        text.void = void

        val renderer = void.renderer as TestRenderer
        while (!GLFW.glfwWindowShouldClose(renderer.window)) {
            renderer.beginFrame()
            renderer.roundedRectangle(0f, 0f, 100f, 100f, 20f, Color.RED)
            text.render()
            renderer.endFrame()
        }

        renderer.destroy()
    }

}