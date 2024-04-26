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

    override fun layout(constraints: BoxConstraints) {
        child.layout(constraints.deflate(padding))
        size = constraints.constrain(Size(child.size.width + padding.horizontal, child.size.height + padding.vertical))
    }

    override fun postLayoutInit(parentOffset: Offset, parent: Widget) {
        super.postLayoutInit(parentOffset + padding.topLeft, parent)  // pass in the child offset
        offset = parentOffset                                                        // and set the correct offset for this widget
    }

    override fun build() = child

}