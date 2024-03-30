package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.Offset
import com.neptuneclient.voidui.widgets.objects.Size
import java.awt.Rectangle
import kotlin.math.round
import kotlin.properties.Delegates

/**
 * The base for every node in the widget tree.
 */
abstract class Widget {
    
    var offset: Offset = Offset.zero
    var size: Size = Size.zero

    /**
     * Holds the screen in which this widget lives.
     */
    protected lateinit var screen: Screen

    /**
     * The parent widget. If this is the first widget in the tree, the parent widget is null.
     */
    protected var parent: Widget? = null

    /**
     * Called by [Screen.init] and initializes the widget. This includes setting position and size as well as initializing the child widget.
     *
     * @param screen The screen in which this widget lives.
     * @param parent The parent widget. If this is the first widget in the tree, the parent widget is null.
     */
    internal open fun init(screen: Screen, parent: Widget?) {
        this.screen = screen
        this.parent = parent

//        val size = layout(screen, parent)
//        x = size.x
//        y = size.y
//        width = size.width
//        height = size.height

        build().init(screen, this)
    }
    
    /**
     * This method is responsible for setting [x], [y], [width] and [height] of the widet.
     */
    open fun layout(constraints: BoxConstraints) {
        // TODO implement this properly
        TODO()
    }

    /**
     * SEE SAME METHOD IN SCREEN CLASS
     */
    open fun recalcOffsets() {

    }

    /**
     * Defines the child node of the widget.
     *
     * @return The child node in the widget tree.
     */
    abstract fun build(): Widget

    /**
     * Lets you declare a property which will trigger the widget to be rebuilt once it's value changes.
     *
     * @param initialValue The initial value of the property.
     *
     * For now this just rebuilds the entire screen.
     */
    protected fun <T> state(initialValue: T) = Delegates.observable(initialValue) { _, _, _ ->
        screen.init()
    }
    
    /**
     * A dsl feature which adds the view-width unit from HTML to components.
     */
    inline val Number.vw
        get() = round(size.width / 100 * this.toFloat()).toInt()

    /**
     * A dsl feature which adds the view-height unit from HTML to components.
     */
    inline val Number.vh
        get() = round(size.height / 100 * this.toFloat()).toInt()

}