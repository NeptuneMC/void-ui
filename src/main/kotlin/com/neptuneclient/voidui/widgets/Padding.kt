package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.BoxConstraints
import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.EdgeInsets

class Padding(
    private val padding: EdgeInsets,
    private val child: Widget
) : Widget() {

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        child.layout(parentOffset + padding.topLeft, constraints.deflate(padding))
        offset = parentOffset
        size = constraints.constrain(Size(child.size.width + padding.horizontal, child.size.height + padding.vertical))
    }

    override fun build() = child

}