package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import com.neptuneclient.voidui.widgets.objects.Offset
import com.neptuneclient.voidui.widgets.objects.Size

/**
 * An internal widget which is used to bundle other widgets.
 *
 * @param children An array with all chidren widgets.
 */
internal class Group(private val children: Array<Widget>) : Widget() {

    override fun init(screen: Screen, parent: Widget?) {
        this.screen = screen
        this.parent = parent

        children.forEach { it.init(screen, this) }
    }

    /**
     * A group doesn't build its own widget, so this return type can be ignored.
     */
    override fun build(): Widget {
        return this
    }

}

class Row(private val children: Array<Widget> = arrayOf(), private val gap: Int = 0) : Widget() {

    override fun build(): Widget {
        return Group(children)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        val gap = gap.toFloat()
        var width = gap * (children.size - 1).coerceAtLeast(0)
        var height = 0f
        val maxPerChild = constraints.maxWidth / children.size
        val childConstraints = constraints.copy(maxWidth = maxPerChild, minWidth = 0f)

        var x = 0F
        children.forEach {
            it.layout(parentOffset + Offset(x, 0F), childConstraints)
            width += it.size.width
            height = maxOf(height, it.size.height)
            x += it.size.width + gap
        }

        /*var x = 0F
        children.forEach {
            it.offset = Offset(x, 0F)
            x += it.size.width + gap
        }*/

        offset = parentOffset
        size = constraints.constrain(Size(width, height))
    }

}

class Column(private val children: Array<Widget> = arrayOf(), private val gap: Int = 0) : Widget() {

    override fun build(): Widget {
        return Group(children)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        val gap = gap.toFloat()
        var width = 0f
        var height = gap * (children.size - 1).coerceAtLeast(0)
        val maxPerChild = constraints.maxHeight / children.size
        val childConstraints = constraints.copy(maxHeight = maxPerChild, minHeight = 0f)

        var y = 0F
        children.forEach {
            it.layout(parentOffset + Offset(0F, y), childConstraints)
            width = maxOf(width, it.size.width)
            height += it.size.height
            y += it.size.height + gap
        }

        /*var y = 0f
        children.forEach {
            it.offset = Offset(0f, y)
            y += it.size.height + gap
        }*/

        offset = parentOffset
        size = constraints.constrain(Size(width, height))
    }

}

class Padding(private val child: Widget, private val padding: EdgeInsets = EdgeInsets.zero) : Widget() {

    override fun build(): Widget {
        return child
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        val innerConstraints = constraints.deflate(padding)
        child.layout(parentOffset + padding.topLeft, innerConstraints)
        //child.offset = padding.topLeft

        offset = parentOffset
        size = constraints.constrain(Size(child.size.width + padding.horizontal, child.size.height + padding.vertical))
    }

}
