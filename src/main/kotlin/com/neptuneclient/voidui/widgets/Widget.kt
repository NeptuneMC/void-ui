package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.event.EventHandler
import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.Offset
import com.neptuneclient.voidui.widgets.objects.Size
import kotlin.math.round
import kotlin.properties.Delegates

/**
 * The base for every node in the widget tree.
 */
abstract class Widget {

    /**
     * A shortcut to [VoidUI.eventHandler].
     */
    protected lateinit var eventHandler: EventHandler

    /**
     * The widget offset from screen's origin position.
     */
    var offset: Offset = Offset.zero

    /**
     * The size of the widget.
     */
    var size: Size = Size.zero

    /**
     * Holds the screen in which this widget lives.
     */
    protected lateinit var screen: Screen

    /**
     * The parent widget. If this is the first widget in the tree, the parent widget is null.
     */
    protected var parent: Widget? = null

    private val root by lazy { build() }

    /**
     * Called by [Screen.init] and initializes the widget. This includes setting position and size as well as initializing the child widget.
     *
     * @param screen The screen in which this widget lives.
     * @param parent The parent widget. If this is the first widget in the tree, the parent widget is null.
     */
    internal open fun init(screen: Screen, parent: Widget?) {
        this.screen = screen
        this.parent = parent
        this.eventHandler = screen.void.eventHandler

        root.init(screen, this)
    }
    
    /**
     * This method is responsible for setting the [size] and the [offset] of the widget.
     * By default, this sets the components size to zero and its offset the same as its parent.
     *
     * @param parentOffset The offset from the origin of the screen from the parent widget.
     * @param constraints The available space in which the widget can be sized.
     */
    open fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        root.layout(parentOffset, constraints)
        offset = parentOffset
        size = root.size
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