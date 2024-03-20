package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.rendering.Renderer
import java.awt.Color

// TODO add docs to this file

class Panel : Element() {
    
    override fun render(renderer: Renderer) {
        renderer.rectangle(x, y, width, height, Color(255, 255, 255, 100))
    }

}