package com.neptuneclient.voidui.ui.elements

import com.neptuneclient.voidui.utils.Font
import java.awt.Color

class Text(
    val text: String,
    val font: Font,
    val color: Color,

    x: Int? = null,
    y: Int? = null,

    margin: Int? = null
) : Element(x, y, null, null, margin) {
    override fun render() {
        draw {
            text(x!!, y!!, text, font, color)
        }
    }

}