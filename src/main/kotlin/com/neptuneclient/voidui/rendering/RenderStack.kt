package com.neptuneclient.voidui.rendering

/**
 * The render stack is responsible for rendering screens.
 *
 * @see com.neptuneclient.voidui.framework.Screen
 */
class RenderStack(private val renderer: Renderer) {

    /**
     * The list which stores the render objects.
     */
    private val stack = mutableListOf<RenderObject>()

    /**
     * Render the whole stack.
     */
    fun render() {
        renderer.beginFrame()
        stack.forEach { it.render(renderer) }
        renderer.endFrame()
    }

    /**
     * Pushes a render object to the render stack.
     *
     * @param renderObject The new render object.
     */
    fun push(renderObject: RenderObject) {
        stack.add(renderObject)
    }

    /**
     * Clears the render stack.
     */
    fun clear() = stack.clear()

}