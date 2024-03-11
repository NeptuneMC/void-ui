package com.neptuneclient.voidui.ui.elements

import com.neptuneclient.voidui.utils.Font
import java.awt.Color

class Text(var text: String, var font: Font, var color: Color, var x: Float, var y: Float): Element() {
    override fun render() {
        draw {
            text(x, y, text, font, color)
        }
    }
}