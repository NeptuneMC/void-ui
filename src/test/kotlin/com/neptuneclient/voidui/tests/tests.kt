package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.utils.Font
import org.junit.jupiter.api.Test
import java.nio.file.Path

class FontTest() {

    val void = VoidUI(MockRenderer())

    @Test
    fun byteBufferTest() {
        val font = Font(void, "test", Path.of("fonts/WorkSans-Regular.ttf"), 10)
        println(font.data.toString())
    }

}