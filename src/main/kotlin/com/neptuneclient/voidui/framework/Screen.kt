package com.neptuneclient.voidui.framework

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.RenderStack

abstract class Screen(voidUI: VoidUI) : Widget() {

    val renderStack = RenderStack(voidUI.renderer)

    init {
        super.voidUI = voidUI
    }

    fun init() {
        root = build()
        root.init(this, this)

        size = Size(width.toFloat(), height.toFloat())
        layout(offset, BoxConstraints.loose(size))
    }

    fun render() {
        renderStack.render()
    }

    override fun remove() {
        root.remove()
        renderStack.clear()
    }

    inline val width
        get() = voidUI.renderer.windowWidth()

    inline val height
        get() = voidUI.renderer.windowHeight()

}