package com.neptuneclient.voidui.ui

import java.awt.Rectangle
import kotlin.math.round

/**
 * The base for every node in the widget tree.
 */
abstract class Widget {
    
    var x: Int = 0
    var y: Int = 0
    var width: Int = 0
    var height: Int = 0

    /**
     * Called by [Screen.init] and initializes the widget. This includes setting position and size as well as initlializing the child widget.
     *
     * @param screen The screen in which this widget lives.
     * @param parent The parent widget. If this is the first widget in the tree, the parent widget is null.
     */
    internal open fun init(screen: Screen, parent: Widget?) {
        val size = sizeSelf(screen, parent)
        x = size.x
        y = size.y
        width = size.width
        height = size.height

        build().init(screen, this)
    }
    
    /**
     * This method is responsible for setting [x], [y], [width] and [height] of the widet.
     */
    open fun sizeSelf(screen: Screen, parent: Widget?): Rectangle {
        // TODO implement this properly
        return Rectangle(10, 20, 30, 40)
    }

    /**
     * Defines the child node of the widget.
     *
     * @return The child node in the widget tree.
     */
    abstract fun build(): Widget
    
    /**
     * A dsl feature which adds the view-width unit from HTML to components.
     */
    inline val Number.vw
        get() = round(width / 100 * this.toFloat()).toInt()

    /**
     * A dsl feature which adds the view-height unit from HTML to components.
     */
    inline val Number.vh
        get() = round(height / 100 * this.toFloat()).toInt()

}