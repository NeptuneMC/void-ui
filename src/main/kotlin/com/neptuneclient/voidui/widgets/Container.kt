package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.EdgeInsets
import java.awt.Color

/**
 * to be done
 */
class Container(
    private val child: Widget,
    private val color: Color = Color(0),

    private val margin: EdgeInsets = EdgeInsets.zero,
    private val padding: EdgeInsets = EdgeInsets.zero,
) : Widget() {

    override fun build(): Widget {
        return Padding(
            padding = margin,
            child = ColoredBox(
                color = color,
                child = Padding(padding, child)
            )
        )
    }

}