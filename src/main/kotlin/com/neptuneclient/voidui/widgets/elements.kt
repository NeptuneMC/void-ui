package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.themes.styles.ImageStyleSheet
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TextStyleSheet
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.utils.Image
import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.Offset
import com.neptuneclient.voidui.widgets.objects.Size
import java.nio.ByteBuffer
import java.nio.file.Path

// TODO add docs to this file

sealed class AbstractPanel(private val child: Widget? = null) : Element<PanelStyleSheet>() {

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        if (child != null)
            build().init(screen, this)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        if (child == null) {
            size = if (styles.border != null)
                constraints.constrain(Size(styles.border!!.sides.horizontal, styles.border!!.sides.vertical))
            else
                constraints.constrain(Size.zero)
            return
        }

        if (styles.border != null) {
            val border = styles.border!!
            val innerConstraints = constraints.deflate(border.sides)
            child.layout(parentOffset + border.sides.topLeft, innerConstraints)
            //child.offset = border.sides.topLeft
            val size = child.size + Size(border.sides.horizontal, border.sides.vertical)
            this.size = constraints.constrain(size)
        }
        child.layout(parentOffset, constraints)

        offset = parentOffset
        size = constraints.constrain(child.size)
    }

    override fun build(): Widget {
        return child!!
    }

    override fun render(renderer: Renderer) {
        renderer.roundedRectangle(offset.x, offset.y, size.width, size.height, styles.radius.toFloat(), styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(offset.x, offset.y, size.width, size.height, styles.radius.toFloat(), styles.border!!.sides.top, styles.border!!.color)
    }

}

class BackgroundPanel(child: Widget? = null) : AbstractPanel(child)
class AccentBackgroundPanel(child: Widget? = null) : AbstractPanel(child)
class Panel(child: Widget? = null) : AbstractPanel(child)
class AccentPanel(child: Widget? = null) : AbstractPanel(child)

sealed class AbstractText(private val label: String) : Element<TextStyleSheet>() {

    private lateinit var font: Font

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        font = Font(screen.void, this.toString(), styles.font, styles.size, styles.letterSpacing)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        val size = screen.void.renderer.getTextBounds(label, font)

        this.offset = parentOffset
        this.size = constraints.constrain(size)
    }

    override fun render(renderer: Renderer) {
        renderer.text(offset.x, offset.y, label, font, styles.color)
    }

}

class Title(label: String) : AbstractText(label)
class SmallTitle(label: String) : AbstractText(label)

class Heading(label: String) : AbstractText(label)
class SmallHeading(label: String) : AbstractText(label)

class Text(label: String) : AbstractText(label)
class SmallText(label: String) : AbstractText(label)

class Image(private val path: Path, private val width: Int, private val height: Int) : Element<ImageStyleSheet>() {

    private lateinit var image: Image

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        image = Image(screen.void, path)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        this.offset = parentOffset
        this.size = Size(width.toFloat(), height.toFloat())
    }

    override fun render(renderer: Renderer) {
        renderer.roundedImage(offset.x, offset.y, size.width, size.height, styles.borderRadius.toFloat(), image)
    }

}