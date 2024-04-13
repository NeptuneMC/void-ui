package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.EventHandler
import com.neptuneclient.voidui.event.MouseClickedEvent
import com.neptuneclient.voidui.event.MouseReleasedEvent
import com.neptuneclient.voidui.units.FontSizeUnit
import com.neptuneclient.voidui.units.PercentUnit
import com.neptuneclient.voidui.units.PixelsUnit
import com.neptuneclient.voidui.units.LengthUnit
import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.Offset
import com.neptuneclient.voidui.widgets.objects.Size
import kotlin.properties.Delegates

/**
 * The base for every node in the widget tree.
 */
abstract class Widget(protected val width: LengthUnit? = null, protected val height: LengthUnit? = null) {

    protected var active = false

    /**
     * A shortcut to [VoidUI.eventHandler].
     */
    protected val eventHandler: EventHandler
        get() = screen.void.eventHandler

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

        root.init(screen, this)

        eventHandler.register(MouseClickedEvent::class) {
            if (hovered())
                active = true
        }
        eventHandler.register(MouseReleasedEvent::class) {
            active = false
            if (hovered())
                widgetClicked()
        }
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

        val width = if (width != null)
            this.width.getPixels(screen, constraints.maxWidth)
        else
            constraints.minWidth

        val height = if (height != null)
            this.height.getPixels(screen, constraints.maxHeight)
        else
            constraints.minWidth

        size = constraints.constrain(Size(width, height))
    }

    /**
     * Defines the child node of the widget.
     *
     * @return The child node in the widget tree.
     */
    abstract fun build(): Widget

    /**
     * Called if the mouse was clicked on the widget and then released on it again.
     */
    open fun widgetClicked() {}

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
     * Checks if the component is hovered by the mouse.
     */
    fun hovered(): Boolean {
        if (!this::screen.isInitialized) return false

        val mousePos = screen.void.renderer.mousePosition()
        return mousePos.x >= offset.x &&
                mousePos.y >= offset.y &&
                mousePos.x < offset.x + size.width &&
                mousePos.y < offset.y + size.height
    }

    /**
     * @see PixelsUnit
     */
    inline val Int.px
        get() = PixelsUnit(this)

    /**
     * @see PercentUnit
     */
    inline val Number.percent
        get() = PercentUnit(this.toFloat())

    /**
     * @see FontSizeUnit
     */
    inline val Number.em
        get() = FontSizeUnit(this.toFloat())

}