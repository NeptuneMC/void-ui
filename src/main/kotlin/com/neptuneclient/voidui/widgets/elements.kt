package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.themes.styles.ImageStyleSheet
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TextStyleSheet
import com.neptuneclient.voidui.units.LengthUnit
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.utils.Image
import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.Offset
import com.neptuneclient.voidui.widgets.objects.Size
import java.awt.Desktop
import java.net.URI
import java.nio.file.Path

// TODO add docs to this file

sealed class AbstractPanel(private val child: Widget? = null, width: LengthUnit? = null, height: LengthUnit? = null) : Element<PanelStyleSheet>(width, height) {

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        if (child != null)
            build().init(screen, this)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        if (width != null && height != null) {
            offset = parentOffset
            size = constraints.constrain(Size(width.getPixels(screen, constraints.maxWidth), height.getPixels(screen, constraints.maxHeight)))

            constraints.maxWidth = size.width
            constraints.maxHeight = size.height
            child?.run { layout(offset, constraints) }
            return
        }

        if (child == null) {
            size = if (styleSheet.border != null)
                constraints.constrain(Size(styleSheet.border!!.sides.horizontal, styleSheet.border!!.sides.vertical))
            else
                constraints.constrain(Size.zero)
            return
        }

        if (styleSheet.border != null) {
            val border = styleSheet.border!!
            val innerConstraints = constraints.deflate(border.sides)
            child.layout(parentOffset + border.sides.topLeft, innerConstraints)
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
        renderer.roundedRectangle(offset.x, offset.y, size.width, size.height, styleSheet.radius.toFloat(), styleSheet.color)
        if (styleSheet.border != null)
            renderer.roundedRectangleFrame(offset.x, offset.y, size.width, size.height, styleSheet.radius.toFloat(), styleSheet.border!!.sides.top, styleSheet.border!!.color)
    }

}

class BackgroundPanel(child: Widget? = null, width: LengthUnit? = null, height: LengthUnit? = null) : AbstractPanel(child, width, height)
class AccentBackgroundPanel(child: Widget? = null) : AbstractPanel(child)
class Panel(child: Widget? = null, width: LengthUnit? = null, height: LengthUnit? = null) : AbstractPanel(child, width, height)
class AccentPanel(child: Widget? = null) : AbstractPanel(child)

sealed class AbstractText(private var label: String) : Element<TextStyleSheet>() {

    private lateinit var font: Font

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        font = Font(screen.void, this.toString(), styleSheet.font, styleSheet.size, styleSheet.letterSpacing)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        val size = screen.void.renderer.getTextBounds(label, font)
        label = label.trim()

        this.offset = parentOffset
        this.size = constraints.constrain(size)
    }

    override fun render(renderer: Renderer) {
        renderer.text(offset.x, offset.y, label, font, styleSheet.color)
        if (styleSheet.underline) {
            val (w, h) = screen.void.renderer.getTextBounds(label, font)
            renderer.rectangle(offset.x, offset.y + h, w, 1F, styleSheet.color)
        }
    }

}

class Title(label: String) : AbstractText(label)
class SmallTitle(label: String) : AbstractText(label)

class Heading(label: String) : AbstractText(label)
class SmallHeading(label: String) : AbstractText(label)

class Text(label: String) : AbstractText(label)
class SmallText(label: String) : AbstractText(label)

class Link(private val address: URI, private var label: String? = null) : Element<TextStyleSheet>() {

    private lateinit var font: Font

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        font = Font(screen.void, this.toString(), styleSheet.font, styleSheet.size, styleSheet.letterSpacing)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        val size = screen.void.renderer.getTextBounds(label ?: address.toString(), font)
        label = label?.trim()

        this.offset = parentOffset
        this.size = constraints.constrain(size)
    }

    override fun render(renderer: Renderer) {
        renderer.text(offset.x, offset.y, label ?: address.toString(), font, styleSheet.color)
        if (styleSheet.underline) {
            val (w, h) = screen.void.renderer.getTextBounds(label ?: address.toString(), font)
            renderer.rectangle(offset.x, offset.y + h, w, 1F, styleSheet.color)
        }
    }

    override fun widgetClicked() {
        Desktop.getDesktop().browse(address)
    }

}

class Image(private val path: Path, width: LengthUnit, height: LengthUnit) : Element<ImageStyleSheet>(width, height) {

    private lateinit var image: Image

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        image = Image(screen.void, path)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        this.offset = parentOffset
        this.size = constraints.constrain(Size(width!!.value, height!!.value))
    }

    override fun render(renderer: Renderer) {
        renderer.roundedImage(offset.x, offset.y, size.width, size.height, styleSheet.borderRadius.toFloat(), image)
    }

}