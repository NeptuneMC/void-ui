package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.themes.StyleSheet
import com.neptuneclient.voidui.themes.objects.Border
import com.neptuneclient.voidui.themes.objects.DropShadow
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TextStyleSheet
import com.neptuneclient.voidui.utils.Font
import java.awt.Color

// TODO add docs to this file

class BackgroundPanel(private val child: Widget? = null) : Element<PanelStyleSheet>() {

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)

        if (child != null)
            build().init(screen, this)
    }

    override fun build(): Widget {
        return child!!
    }

    override fun render(renderer: Renderer) {
        renderer.roundedRectangle(x, y, width, height, styles.radius, styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), styles.border!!.width, styles.radius.toFloat(), styles.border!!.color)
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
        renderer.roundedRectangle(x, y, width, height, styles.radius, styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), styles.border!!.width, styles.radius.toFloat(), styles.border!!.color)
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
        renderer.roundedRectangle(x, y, width, height, styles.radius, styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), styles.border!!.width, styles.radius.toFloat(), styles.border!!.color)
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
        renderer.roundedRectangle(x, y, width, height, styles.radius, styles.color)
        if (styles.border != null)
            renderer.roundedRectangleFrame(x.toFloat(), y.toFloat(), width.toFloat(), height.toFloat(), styles.border!!.width, styles.radius.toFloat(), styles.border!!.color)
    }

}

class Text(private val label: String) : Element<TextStyleSheet>() {

    private lateinit var font: Font

    override fun init(screen: Screen, parent: Widget?) {
        super.init(screen, parent)
        font = Font(screen.void, this.toString(), styles.font, styles.size)
        screen.void.renderer.registerFont(font)
    }

    override fun render(renderer: Renderer) {
        renderer.text(x, y, label, font, styles.color)
    }

}