package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.BoxConstraints
import com.neptuneclient.voidui.framework.GroupWidget
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget
import kotlin.math.max

/**
 * A widget which layers multiple widgets on top of each other.
 *
 * @param children The array of children in the stack.
 */
class Stack(private val children: Array<Widget>) : GroupWidget() {

    override fun layout(constraints: BoxConstraints) {
        var childWidth = 0f
        var childHeight = 0f

        children.forEach {
            it.layout(constraints)
            childWidth = max(childWidth, it.size.width)
            childHeight = max(childHeight, it.size.height)
        }
        size = constraints.constrain(Size(childWidth, childHeight))
    }

    override fun buildGroup(): Array<Widget> {
        return children
    }

}