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

//        val size = layout(screen, parent)
//        x = size.x
//        y = size.y
//        width = size.width
//        height = size.height

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

    override fun layout(constraints: BoxConstraints) {
        var width = 0f
        var height = 0f
        val gap = gap.toFloat()
        val maxPerChild = constraints.maxWidth / children.size
        val childConstraints = constraints.copy(maxWidth = maxPerChild, minWidth = 0f)

        children.forEach {
            it.layout(childConstraints)
            val size = it.size
            width += size.width
            height = maxOf(height, size.height)
        }

        size = constraints.constrain(Size(width, height))
    }

    override fun recalcOffsets() {
        children.forEach {
            it.offset += offset
        }
    }
}

class Column(private val children: Array<Widget> = arrayOf(), private val gap: Int = 0) : Widget() {

    override fun build(): Widget {
        return Group(children)
    }

    override fun layout(constraints: BoxConstraints) {
        val gap = gap.toFloat()
        var width = 0f
        var height = gap * (children.size - 1).coerceAtLeast(0)
        val maxPerChild = constraints.maxHeight / children.size
        val childConstraints = constraints.copy(maxHeight = maxPerChild, minHeight = 0f)

        children.forEach {
            it.layout(childConstraints)
            val size = it.size
            width = maxOf(width, size.width)
            height += size.height
        }

        var y = 0f
        children.forEach {
            it.offset = Offset(0f, y)
            y += it.size.height + gap
        }

        size = constraints.constrain(Size(width, height))
    }

    override fun recalcOffsets() {
        children.forEach {
            it.offset += offset
            it.recalcOffsets()
            println(it.offset)
        }
    }
}

class Padding(private val child: Widget, private val padding: EdgeInsets = EdgeInsets.zero) : Widget() {

    override fun build(): Widget {
        return child
    }

    override fun layout(constraints: BoxConstraints) {
        val innerConstraints = constraints.deflate(padding)
        child.layout(innerConstraints)
        child.offset = padding.topLeft
        size = constraints.constrain(Size(child.size.width + padding.horizontal, child.size.height + padding.vertical))
    }

    override fun recalcOffsets() {
        child.offset += offset
        child.recalcOffsets()
        println(child.offset)
    }
}
