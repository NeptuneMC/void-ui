package com.neptuneclient.voidui.rendering

import com.neptuneclient.voidui.framework.LeafWidget
import com.neptuneclient.voidui.framework.Widget

/**
 * The render stack is responsible for rendering screens.
 *
 * @see com.neptuneclient.voidui.framework.Screen
 */
class RenderStack(private val renderer: Renderer) {

    /**
     * The list which stores the renderable leaf widgets.
     */
    private val stack = mutableListOf<LeafWidget>()

    /**
     * Render the whole stack.
     */
    fun render() {
        renderer.beginFrame()
        stack.forEach { it.render(renderer) }
        renderer.endFrame()
    }

    /**
     * Pushes a widget to the render stack.
     *
     * @param widget The new widget.
     */
    fun push(widget: LeafWidget) {
        stack.add(widget)
    }

    /**
     * Clears the render stack.
     */
    fun clear() = stack.clear()

}