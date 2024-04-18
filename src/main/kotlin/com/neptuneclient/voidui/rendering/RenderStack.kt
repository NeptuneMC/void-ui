package com.neptuneclient.voidui.rendering

import com.neptuneclient.voidui.framework.LeafWidget

/**
 * The element stack is responsible for rendering screens.
 *
 * @see Screen
 */
class RenderStack(private val renderer: Renderer) {

    /**
     * The list which stores the elements.
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
     * Pushes an element ontop of the render stack.
     *
     * @param element The new element on the stack.
     */
    fun push(element: LeafWidget) {
        stack.add(element)
    }

    /**
     * Clears the element stack.
     */
    fun clear() = stack.clear()

}