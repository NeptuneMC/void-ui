package com.neptuneclient.voidui.rendering

import com.neptuneclient.voidui.ui.Element
import com.neptuneclient.voidui.ui.Screen

/**
 * The element stack is responsible for rendering screens.
 *
 * @see Screen
 */
class ElementStack
/**
 * @param renderer The renderer which will render the element stack.
 */
constructor(private val renderer: Renderer) {

    /**
     * The list which stores the elements.
     */
    private val stack = mutableListOf<Element>()

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
    fun push(element: Element) {
        stack.add(element)
    }

    /**
     * Clears the element stack.
     */
    fun clear() = stack.clear()

}