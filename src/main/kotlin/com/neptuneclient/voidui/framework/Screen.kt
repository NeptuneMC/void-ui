package com.neptuneclient.voidui.framework

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.RenderStack
import com.neptuneclient.voidui.widgets.Align
import com.neptuneclient.voidui.widgets.Alignment

/**
 * A screen which can render widgets.
 *
 * @param voidUI The instance of void ui.
 */
abstract class Screen(voidUI: VoidUI) : Widget() {

    /**
     * The render stack used to render the screen.
     */
    val renderStack = RenderStack(voidUI.renderer)

    init {
        super.voidUI = voidUI
    }

    /**
     * Initializes the screen.
     *
     * **Needs to be called by the user.**
     */
    fun init() {
        val template = voidUI.template.build(this.build())
        root = if (voidUI.settings.centeredScreen)
            Align(template, Alignment.center)
        else
            template
        root.init(this, this)

        size = Size(width.toFloat(), height.toFloat())
        layout(BoxConstraints.loose(size))
        root.postLayoutInit(offset, this)
    }

    /**
     * Renders a frame of the screen.
     *
     * **Needs to be called by the user.**
     */
    fun render() {
        renderStack.render()
    }

    /**
     * Cleans up any resources from the screen.
     *
     * **Needs to be called by the user.**
     */
    override fun remove() {
        root.remove()
        renderStack.clear()
    }

    /**
     * The width of the screen in pixels.
     */
    inline val width
        get() = voidUI.renderer.windowWidth()

    /**
     * The height of the screen in pixels.
     */
    inline val height
        get() = voidUI.renderer.windowHeight()

}