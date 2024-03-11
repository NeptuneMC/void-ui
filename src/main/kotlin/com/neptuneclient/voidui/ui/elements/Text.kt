package com.neptuneclient.voidui.ui.elements

import com.neptuneclient.voidui.ui.objects.EdgeInsets
import com.neptuneclient.voidui.utils.Font
import java.awt.Color

class Text(
    val text: String,
    val font: Font,
    val color: Color,
    val backgroundColor: Color = Color(0, 0, 0, 0),

    x: Int? = null,
    y: Int? = null,

    margin: EdgeInsets = EdgeInsets.zero,
    padding: EdgeInsets = EdgeInsets.zero
) : Element(x, y, null, null, margin, padding) {
    override fun render() {
        draw {
            val (width, height) = getTextBounds(text, font)
            val rectX = x!! - padding.left
            val rectY = y!! - height - padding.top
            rectangle(rectX, rectY, padding.horizontal + width, padding.vertical + height, backgroundColor)
            text(x!!, y!!, text, font, color)
            rectangle(x!!, y!!, 1, 1, Color(255, 0, 0))
        }
    }

}