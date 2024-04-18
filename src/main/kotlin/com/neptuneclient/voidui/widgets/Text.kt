package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.*
import com.neptuneclient.voidui.objects.TextAlign
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.utils.Font
import java.awt.Color
import java.nio.file.Path

class Text(
    private val label: String = "",
    private val align: TextAlign = TextAlign.START,
    private val style: TextStyle = TextStyle(Font("name", Path.of("fonts/WorkSans-Regular.ttf")), Color.WHITE, 16, 0f),
) : LeafWidget() {

    override fun init(screen: Screen, parent: Widget) {
        super.init(screen, parent)
        style.font.register(screen.voidUI.renderer)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        val textMetrics = screen.voidUI.renderer.getTextBounds(label, style.font, style)
        size = constraints.constrain(textMetrics)

        offset = when (align) {
            TextAlign.START -> parentOffset
            TextAlign.CENTER -> parentOffset + Offset((constraints.maxWidth - size.width) / 2F, 0F)
            TextAlign.END -> parentOffset + Offset(constraints.maxWidth - size.width, 0F)
        }
    }

    override fun render(renderer: Renderer) {
        renderer.text(offset.x, offset.y, label, style.font, style)
    }
}

data class TextStyle(val font: Font, val color: Color, val size: Int, val letterSpacing: Float)