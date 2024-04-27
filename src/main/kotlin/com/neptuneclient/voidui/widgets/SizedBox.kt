package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.BoxConstraints
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget

/**
 * A widget which has a fixed size.
 *
 * @param child The child widget.
 * @param width The width of the box.
 * @param height The height of the box.
 */
class SizedBox(
    private val child: Widget,
    private val width: Float? = null,
    private val height: Float? = null,
) : Widget() {

    override fun layout(constraints: BoxConstraints) {
        val childConstraints = BoxConstraints(
            constraints.minWidth,
            width ?: constraints.maxWidth,
            constraints.minHeight,
            height ?: constraints.maxHeight
        )
        child.layout(childConstraints)

        val s = Size(width ?: child.size.width, height ?: child.size.height)
        size = constraints.constrain(s)
    }

    override fun build(): Widget {
        return child
    }

}