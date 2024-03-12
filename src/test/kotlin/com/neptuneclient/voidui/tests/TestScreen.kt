package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.ui.Drawable
import com.neptuneclient.voidui.ui.Screen
import com.neptuneclient.voidui.ui.elements.Text
import com.neptuneclient.voidui.utils.Font
import java.nio.file.Path

class TestScreen(void: VoidUI) : Screen(void) {

    // temp
    private val font = Font(void, "screen", Path.of("fonts/WorkSans-Regular.ttf"), 16)

    override fun build(): Drawable {
        return Text(
            text = "Deutschland über alle",
            font = font,

            x = 50.vw,
            y = 50.vh
        )
    }

}