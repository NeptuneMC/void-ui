package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.rendering.Renderer
import java.awt.Color

// TODO add docs to this file

class Group(private val children: Array<Widget>) : Element() {
    
    override fun init(screen: Screen, parent: Widget?) {
        val size = sizeSelf(screen, parent)
        x = size.x
        y = size.y
        width = size.width
        height = size.height
        
        children.forEach { it.init(screen, this) }
    }
    
    override fun render(renderer: Renderer) {
    }

}

class Panel : Element() {
    
    override fun render(renderer: Renderer) {
        renderer.rectangle(x, y, width, height, Color(255, 255, 255, 100))
    }

}