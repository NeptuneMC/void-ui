package com.neptuneclient.voidui.rendering

import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size

/**
 * An object which can be created by a widget. It renders things to the screen. The convention when creating
 * a render object class is to use the widget name and append "RenderObject" to it.
 *
 * @param offset The offset (x, y) of the render object.
 * @param size The size of the render object box.
 */
abstract class RenderObject(val offset: Offset, val size: Size) {

    /**
     * Called for every frame in [RenderStack.render].
     *
     * @param renderer The renderer which renders the screen.
     */
    abstract fun render(renderer: Renderer)

}
