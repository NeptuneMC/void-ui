package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.BoxConstraints
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget
import kotlin.math.max

/**
 * A widget which has a fixed size.
 *
 * @param child The child widget.
 * @param width The width of the box.
 * @param height The height of the box.
 */
class SizedBox(
    private val child: Widget,
    private val width: Float = 0f,
    private val height: Float = 0f,
) : Widget() {

    override fun layout(constraints: BoxConstraints) {
        child.layout(constraints)

        val s = Size(max(width, child.size.width), max(height, child.size.height))
        size = constraints.constrain(s)
    }

    override fun build(): Widget {
        return child
    }

}