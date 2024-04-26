package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.*
import com.neptuneclient.voidui.objects.TextAlign
import com.neptuneclient.voidui.rendering.RenderObject
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.theme.TextStyle

/**
 * A widget which renders text to the screen.
 *
 * @param label The text to be rendered.
 * @param align The alignment of the text.
 * @param textStyle A custom style object for the text.
 * @param type The type of the text, e.g. Regular or Large.
 */
class Text(
    private val label: String = "",
    private val align: TextAlign = TextAlign.START,
    private val textStyle: TextStyle? = null,
    private val type: TextType = TextType.REGULAR
) : LeafWidget() {

    private lateinit var style: TextStyle

    override fun init(screen: Screen, parent: Widget) {
        super.init(screen, parent)

        style = textStyle ?: when (type) {
            TextType.SMALL -> theme.defaultStyles.smallText
            TextType.REGULAR -> theme.defaultStyles.regularText
            TextType.MEDIUM -> theme.defaultStyles.mediumText
            TextType.LARGE -> theme.defaultStyles.largeText
        }
        style.font.register(screen.voidUI.renderer)
    }

    override fun layout(constraints: BoxConstraints) {
        val textMetrics = screen.voidUI.renderer.getTextBounds(label, style.font, style)
        size = constraints.constrain(textMetrics)
    }

    override fun postLayoutInit(parentOffset: Offset, parent: Widget) {
        super.postLayoutInit(parentOffset, parent)
        offset = when (align) {
            TextAlign.START -> parentOffset
            TextAlign.CENTER -> parentOffset + Offset((parent.size.width - size.width) / 2F, 0F)
            TextAlign.END -> parentOffset + Offset(parent.size.width - size.width, 0F)
        }
    }

    override fun createRenderObject(): RenderObject? {
        return TextRenderObject(offset, size, label, style)
    }
}

private class TextRenderObject(offset: Offset, size: Size, val text: String, val style: TextStyle) : RenderObject(offset, size) {
    override fun render(renderer: Renderer) {
        renderer.text(offset, text, style.font, style)
    }
}

enum class TextType { SMALL, REGULAR, MEDIUM, LARGE }