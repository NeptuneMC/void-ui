package com.neptuneclient.voidui.framework

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.Event
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.theme.Theme
import com.neptuneclient.voidui.widgets.Placeholder
import kotlin.properties.Delegates
import kotlin.reflect.KClass

/**
 * The base for every node in the widget tree.
 */
abstract class Widget {

    /**
     * Quick access to the void ui instance.
     */
    lateinit var voidUI: VoidUI

    /**
     * Quick access to the screen instance.
     */
    protected lateinit var screen: Screen

    /**
     * The offset from the screen origin.
     */
    var offset = Offset.zero

    /**
     * The size of the widget.
     */
    var size = Size.zero

    /**
     * The child widget defined in [build].
     */
    protected lateinit var root: Widget

    /**
     * Builds the widgets children tree.
     *
     * @return The top of the child tree.
     */
    abstract fun build(): Widget

    /**
     * Initializes the widget.
     *
     * @param screen The screen in which the widget is initialized.
     * @param parent The parent widget.
     */
    internal open fun init(screen: Screen, parent: Widget) {
        this.voidUI = screen.voidUI
        this.screen = screen

        root = build()
        root.init(screen, this)
    }

    /**
     * This is responsible for setting [offset] and [size] of the widget.
     *
     * @param parentOffset The offset of the parent widget.
     * @param constraints The constraints of the widget's size.
     */
    open fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        root.layout(parentOffset, constraints)

        offset = parentOffset
        size = constraints.constrain(root.size)
    }

    /**
     * Cleans up code from the widget when it is removed from the tree.
     */
    open fun remove() {
        root.remove()
        voidUI.eventHandler.unregister(this)
    }

    /**
     * Defines a stateful variable, which rebuilds the widget once it changes it's value.
     */
    fun <T> stateOf(initialValue: T) = Delegates.observable(initialValue) { _, _, _ ->
        // redo ui
    }

    /**
     * Registers a new event action.
     *
     * @see com.neptuneclient.voidui.event.EventHandler.register
     */
    fun <T : Event> registerEventAction(event: KClass<T>, action: (event: T) -> Unit) {
        voidUI.eventHandler.register(event, this, action)
    }

}

/**
 * A special type of widget which does not build a widget tree but instead renders directly to the screen. These
 * types of widgets will be pushed onto the [com.neptuneclient.voidui.rendering.RenderStack] and rendered for
 * every frame.
 */
abstract class LeafWidget : Widget() {

    /**
     * A shortcut to the current theme from the library instance.
     */
    protected val theme: Theme
        get() = screen.voidUI.theme

    /**
     * Initializes the widget.
     *
     * @param screen The screen in which the widget is initialized.
     * @param parent The parent widget.
     */
    override fun init(screen: Screen, parent: Widget) {
        this.voidUI = screen.voidUI
        this.screen = screen

        screen.renderStack.push(this)
    }

    /**
     * This is responsible for setting [offset] and [size] of the widget.
     *
     * @param parentOffset The offset of the parent widget.
     * @param constraints The constraints of the widget's size.
     */
    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        offset = parentOffset
        size = Size(constraints.minWidth, constraints.minHeight)
    }

    /**
     * This method is never called on a leaf widget, that is why it only returns a placeholder widget.
     */
    final override fun build(): Widget {
        return Placeholder()
    }

    /**
     * Cleans up code from the widget when it is removed from the tree.
     */
    override fun remove() {
        voidUI.eventHandler.unregister(this)
    }

    /**
     * Renders the widget to the screen.
     *
     * @param renderer The renderer of the library instance.
     */
    abstract fun render(renderer: Renderer)

}