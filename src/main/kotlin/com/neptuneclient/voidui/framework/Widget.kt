package com.neptuneclient.voidui.framework

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.Event
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.theme.Theme
import com.neptuneclient.voidui.widgets.Placeholder
import kotlin.properties.Delegates
import kotlin.reflect.KClass

abstract class Widget {

    lateinit var voidUI: VoidUI

    protected lateinit var screen: Screen

    var offset = Offset.zero
    var size = Size.zero

    protected lateinit var root: Widget

    abstract fun build(): Widget

    internal open fun init(screen: Screen, parent: Widget) {
        this.voidUI = screen.voidUI
        this.screen = screen

        root = build()
        root.init(screen, this)
    }

    open fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        root.layout(parentOffset, constraints)

        offset = parentOffset
        size = constraints.constrain(root.size)
    }

    open fun remove() {
        root.remove()
    }

    fun <T> stateOf(initialValue: T) = Delegates.observable(initialValue) { _, _, _ ->
        // redo ui
    }

    fun <T : Event> registerEventAction(event: KClass<T>, action: (event: T) -> Unit) {
        voidUI.eventHandler.register(event, this, action)
    }

}

abstract class LeafWidget : Widget() {

    protected val theme: Theme
        get() = screen.voidUI.theme

    override fun init(screen: Screen, parent: Widget) {
        this.voidUI = screen.voidUI
        this.screen = screen

        screen.renderStack.push(this)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        offset = parentOffset
        size = Size(constraints.minWidth, constraints.minHeight)
    }

    final override fun build(): Widget {
        return Placeholder()
    }

    override fun remove() {}

    abstract fun render(renderer: Renderer)

}