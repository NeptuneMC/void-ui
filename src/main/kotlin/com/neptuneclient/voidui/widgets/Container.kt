package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.Border
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.objects.EdgeInsets
import java.awt.Color

/**
 * A widget which holds other widgets. Optionally it can also have a background color
 * and a border.
 *
 * @param child The child widget of the container.
 * @param color The background color of the container.
 * @param cornerRadius The corner radius of the background box.
 * @param border The border of the container.
 * @param margin The margin between the parent and the border.
 * @param padding The padding between the border and the child.
 * @param width The optional fixed width of the container.
 * @param height The optional fixed height of the container.
 */
class Container(
    private val child: Widget,
    private val color: Color = Color(0),

    private val cornerRadius: CornerRadius = CornerRadius.zero,
    private val border: Border = Border(0f, Color(0)),

    private val margin: EdgeInsets = EdgeInsets.zero,
    private val padding: EdgeInsets = EdgeInsets.zero,

    private val width: Float? = null,
    private val height: Float? = null,
) : Widget() {

    override fun build(): Widget {
        return Padding(
            padding = margin,
            child = ColoredBox(
                color = color,
                border = border,
                cornerRadius = cornerRadius,
                child = if (width != null || height != null) {
                    val w = width ?: 0f
                    val h = height ?: 0f
                    SizedBox(
                        width = w,
                        height = h,
                        child = Padding(padding, child)
                    )
                } else {
                    Padding(padding, child)
                }
            )
        )
    }

}
