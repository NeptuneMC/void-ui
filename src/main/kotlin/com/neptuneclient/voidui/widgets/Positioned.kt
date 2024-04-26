package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Widget

/**
 * A widget which positions another widget absolutely on the screen.
 *
 * @param child The child widget.
 * @param position The position of the widget.
 */
class Positioned(private val child: Widget, private val position: Offset? = null) : Widget() {

    override fun postLayoutInit(parentOffset: Offset, parent: Widget) {
        super.postLayoutInit(position ?: parentOffset, parent)
    }

    override fun build(): Widget {
        return child
    }

}