package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TextStyleSheet
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.Size

// TODO add docs to this file

class BackgroundPanel(private val child: Widget? = null) : Element<PanelStyleSheet>() {

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)

        if (child != null)
            build().init(screen, this)
    }

    override fun layout(constraints: BoxConstraints) {
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
            child.layout(innerConstraints)
            child.offset = border.sides.topLeft
            val size = child.size + Size(border.sides.horizontal, border.sides.vertical)
            this.size = constraints.constrain(size)
        }
        child.layout(constraints)
        size = constraints.constrain(child.size)
    }

    override fun recalcOffsets() {
        println("Recalculating offsets")
        if (child != null) {
            child.offset += offset
            child.recalcOffsets()
        }
    }

    override fun build(): Widget {
        return child!!
    }

    override fun render(renderer: Renderer) {
        renderer.roundedRectangle(offset.x, offset.y, size.width, size.height, styles.radius.toFloat(), styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(offset.x, offset.y, size.width, size.height, styles.border!!.sides, styles.radius.toFloat(), styles.border!!.color)
    }

}

class AccentBackgroundPanel(private val child: Widget? = null) : Element<PanelStyleSheet>() {

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)

        if (child != null)
            build().init(screen, this)
    }

    override fun build(): Widget {
        return child!!
    }

    override fun render(renderer: Renderer) {
        renderer.roundedRectangle(offset.x, offset.y, size.width, size.height, styles.radius.toFloat(), styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(offset.x, offset.y, size.width, size.height, styles.border!!.sides, styles.radius.toFloat(), styles.border!!.color)
    }

}

class Panel(private val child: Widget? = null) : Element<PanelStyleSheet>() {

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)

        if (child != null)
            build().init(screen, this)
    }

    override fun build(): Widget {
        return child!!
    }
    
    override fun render(renderer: Renderer) {
        renderer.roundedRectangle(offset.x, offset.y, size.width, size.height, styles.radius.toFloat(), styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(offset.x, offset.y, size.width, size.height, styles.border!!.sides, styles.radius.toFloat(), styles.border!!.color)
    }

}

class AccentPanel(private val child: Widget? = null) : Element<PanelStyleSheet>() {

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)

        if (child != null)
            build().init(screen, this)
    }

    override fun build(): Widget {
        return child!!
    }

    override fun render(renderer: Renderer) {
        renderer.roundedRectangle(offset.x, offset.y, size.width, size.height, styles.radius.toFloat(), styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(offset.x, offset.y, size.width, size.height, styles.border!!.sides, styles.radius.toFloat(), styles.border!!.color)
    }

}

class Text(private val label: String) : Element<TextStyleSheet>() {

    private lateinit var font: Font

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        font = Font(screen.void, this.toString(), styles.font, styles.size)
        screen.void.renderer.registerFont(font)
    }

    override fun layout(constraints: BoxConstraints) {
        val size = screen.void.renderer.getTextBounds(label, font)
        this.size = constraints.constrain(size)
    }

    override fun render(renderer: Renderer) {
//        println("Rendering at $offset")
        renderer.text(offset.x, offset.y, label, font, styles.color)
    }

}