package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.*
import kotlin.math.max

/**
 * A group widget which lets you place widgets in a row.
 *
 * @param children The widgets in the row.
 * @param gap The gap between the widgets.
 */
class Row(private val children: Array<Widget>, private val gap: Float = 0f) : GroupWidget() {

    override fun layout(constraints: BoxConstraints) {
        var w = gap * (children.size - 1)
        var h = 0f
        children.forEach {
            it.layout(constraints)
            w += it.size.width
            h = max(h, it.size.height)
        }
        size = constraints.constrain(Size(w, h))
    }

    override fun postLayoutInit(parentOffset: Offset, parent: Widget) {
        var x = parentOffset.x
        children.forEach {
            it.postLayoutInit(Offset(x, parentOffset.y), this)
            x += it.size.width + gap
        }
        offset = parentOffset
    }

    override fun buildGroup(): Array<Widget> {
        return children
    }

}