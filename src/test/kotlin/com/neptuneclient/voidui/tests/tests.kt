package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.ui.elements.Text
import com.neptuneclient.voidui.ui.objects.EdgeInsets
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
            color = Color.WHITE,
            padding = EdgeInsets.all(50f),
            backgroundColor = Color.BLUE,
        )
        text.void = void

        val screen = TestScreen(void)
        screen.assemble()

        val renderer = void.renderer as TestRenderer
        while (!GLFW.glfwWindowShouldClose(renderer.window)) {
            screen.render()
        }

        renderer.destroy()
    }

}