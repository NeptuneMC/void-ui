package com.neptuneclient.voidui.framework

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.Event
import com.neptuneclient.voidui.rendering.RenderObject
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
     * This is responsible for setting the [size] of the widget.
     *
     * @param constraints The constraints of the widget's size.
     */
    open fun layout(constraints: BoxConstraints) {
        root.layout(constraints)
        size = constraints.constrain(root.size)
    }

    /**
     * Initializes things which require the [size] property from the widget, for example its offset from the screen's initial position.
     * This method is called after the [layout] method, so the size is already set.
     *
     * @param parentOffset The offset of the parent widget.
     * @param parent The parent widget in the tree.
     */
    open fun postLayoutInit(parentOffset: Offset, parent: Widget) {
        offset = parentOffset

        val renderable = createRenderObject()
        if (renderable != null)
            screen.renderStack.push(renderable)

        root.postLayoutInit(parentOffset, this)
    }

    /**
     * Pushes a render object to the render stack.
     */
    open fun createRenderObject(): RenderObject? = null

    /**
     * Cleans up code from the widget when it is removed from the tree.
     */
    open fun remove() {
        root.remove()
        voidUI.eventHandler.unregister(this)
    }

    /**
     * Defines a stateful variable, which rebuilds the widget once it changes its value.
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
    }

    /**
     * This is responsible for setting the [size] of the widget.
     *
     * @param constraints The constraints of the widget's size.
     */
    override fun layout(constraints: BoxConstraints) {
        size = Size(constraints.minWidth, constraints.minHeight)
    }

    /**
     * Initializes things which require the [size] property from the widget, for example its offset from the screen's initial position.
     * This method is called after the [layout] method, so the size is already set.
     *
     * @param parentOffset The offset of the parent widget.
     * @param parent The parent widget in the tree.
     */
    override fun postLayoutInit(parentOffset: Offset, parent: Widget) {
        offset = parentOffset

        val renderable = createRenderObject()
        if (renderable != null)
            screen.renderStack.push(renderable)
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

}