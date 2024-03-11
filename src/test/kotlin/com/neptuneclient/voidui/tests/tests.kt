package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.utils.Font
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW
import java.awt.Color
import java.nio.file.Path

class FontTest() {

    val void = VoidUI(TestRenderer())

    @Test
    // Disable if running in CI
    @DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")
    fun byteBufferTest() {
        val font = Font(void, "test", Path.of("fonts/WorkSans-Regular.ttf"), 30)
        println(font.data.toString())

        val renderer = void.renderer as TestRenderer
        while (!GLFW.glfwWindowShouldClose(renderer.window)) {
            renderer.beginFrame()
            renderer.roundedRectangle(0f, 0f, 100f, 100f, 20f, Color.RED)
            renderer.text(100f, 100f, "Hello, World!", font, Color.WHITE)
            renderer.endFrame()
        }

        renderer.destroy()
    }

}