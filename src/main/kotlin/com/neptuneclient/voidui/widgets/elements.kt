package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TextStyleSheet
import com.neptuneclient.voidui.utils.Font

// TODO add docs to this file

class Panel : Element<PanelStyleSheet>() {
    
    override fun render(renderer: Renderer) {
        renderer.roundedRectangle(x, y, width, height, styles.radius, styles.color)
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