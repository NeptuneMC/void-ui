package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TestStyleSheet

// TODO add docs to this file

class Panel : Element<PanelStyleSheet>() {
    
    override fun render(renderer: Renderer) {
        renderer.roundedRectangle(x, y, width, height, styles.radius, styles.color)
    }

}

class TestElement : Element<TestStyleSheet>() {

    override fun render(renderer: Renderer) {
    }

}