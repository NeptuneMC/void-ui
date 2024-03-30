package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.widgets.objects.EdgeInsets

/**
 * An internal widget which is used to bundle other widgets.
 *
 * @param children An array with all chidren widgets.
 */
internal class Group(private val children: Array<Widget>) : Widget() {

    override fun init(screen: Screen, parent: Widget?) {
        this.screen = screen
        this.parent = parent

        val size = sizeSelf(screen, parent)
        x = size.x
        y = size.y
        width = size.width
        height = size.height

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

}

class Column(private val children: Array<Widget> = arrayOf(), private val gap: Int = 0) : Widget() {

    override fun build(): Widget {
        return Group(children)
    }

}

class Padding(private val child: Widget, private val padding: EdgeInsets = EdgeInsets.zero) : Widget() {

    override fun build(): Widget {
        return child
    }

}
