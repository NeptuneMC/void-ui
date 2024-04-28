package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.BoxConstraints
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget

/**
 * A sized box which adjusts its size according to the maximum constraints of its parent.
 *
 * @param child The child widget of the sized box.
 * @param width The width factor for the constraints.
 * @param height The height factor for the constraints.
 */
class FractionallySizedBox(
    private val child: Widget,
    private val width: Double = 1.0,
    private val height: Double = 1.0
) : Widget() {

    override fun layout(constraints: BoxConstraints) {
        size = Size(constraints.maxWidth * width.toFloat(), constraints.maxHeight * height.toFloat())
    }

    override fun build(): Widget {
        return child
    }

}