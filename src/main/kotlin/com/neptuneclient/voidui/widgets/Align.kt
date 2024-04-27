package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.BoxConstraints
import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget

/**
 * A widget which aligns a child at a relative position.
 * It stretches itself to the maximum size of the constraints.
 *
 * @param child The child which will be aligned.
 * @param alignment The alignment factor.
 */
class Align(
    private val child: Widget,
    private val alignment: Alignment = Alignment.topLeft
) : Widget() {

    override fun layout(constraints: BoxConstraints) {
        child.layout(constraints)
        size = Size(constraints.maxWidth, constraints.maxHeight)
    }

    override fun postLayoutInit(parentOffset: Offset, parent: Widget) {
        super.postLayoutInit(parentOffset + alignment.align(size, child.size), parent)
        offset = parentOffset
    }

    override fun build(): Widget {
        return child
    }

}

/**
 * A data class to hold values about the relative alignment of a widget inside its parent.
 *
 * @param x The x-axis alignment.
 * @param y The y-axis alignment.
 */
data class Alignment(val x: Double, val y: Double) {

    companion object {
        val topLeft = Alignment(-1.0, -1.0)

        val topCenter = Alignment(0.0, -1.0)

        val topRight = Alignment(1.0, -1.0)

        val centerLeft = Alignment(-1.0, 0.0)

        val center = Alignment(0.0, 0.0)

        val centerRight = Alignment(1.0, 0.0)

        val bottomLeft = Alignment(-1.0, 1.0)

        val bottomCenter = Alignment(0.0, 1.0)

        val bottomRight = Alignment(1.0, 1.0)
    }

    /**
     * Aligns a child size within the parent's size according to [x] and [y].
     *
     * @param baseSize The larger size which acts as a container for the smaller size.
     * @param childSize The smaller size which will be aligned.
     *
     * @return The aligned offset from the top left corner of the base size.
     */
    fun align(baseSize: Size, childSize: Size): Offset {
        // x and y values between 0 and 1
        val relX = (x + 1) / 2f
        val relY = (y + 1) / 2f
        val size = baseSize - childSize

        return Offset(size.width * relX.toFloat(), size.height * relY.toFloat())
    }

}