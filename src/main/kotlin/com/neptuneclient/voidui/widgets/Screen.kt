package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.ElementStack
import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.Constraints
import com.neptuneclient.voidui.widgets.objects.Offset
import com.neptuneclient.voidui.widgets.objects.Size
import kotlin.math.round
import kotlin.properties.Delegates

/**
 * The base class for screens. A screen is the base node of a widget tree, which can be rendered to the screen.
 */
abstract class Screen
/**
 * @param void The singleton of the library main class.
 */
constructor(val void: VoidUI) {

    /**
     * The size of the screen.
     */
    val size = Size(void.renderer.windowWidth().toFloat(), void.renderer.windowHeight().toFloat())

    /**
     * The element stack of the screen.
     */
    val elementStack = ElementStack(void.renderer)

    /**
     * The first widget in the tree.
     */
    val root: Widget by lazy { build() }

    /**
     * Build the widget tree of the screen.
     *
     * @return The first node of the tree.
     */
    abstract fun build(): Widget

    /**
     * Call this meothod to initialize the screen and build the widget tree.
     */
    fun init() {
        elementStack.clear()
        root.init(this, null)

        layout()
    }

    private fun layout() {
        root.layout(Offset(0F, 0F), BoxConstraints.loose(size))
    }

    /**
     * Call this method to render the element stack.
     */
    fun render() {
        elementStack.render()
    }

    /**
     * Lets you declare a property which will trigger the screen to be rebuilt once it's value changes.
     *
     * @param initialValue The initial value of the property.
     */
    protected fun <T> state(initialValue: T) = Delegates.observable(initialValue) { _, _, _ ->
        init()
    }

    /**
     * A dsl feature which adds the view-width unit from HTML to screens.
     */
    inline val Number.vw
        get() = round(size.width / 100 * this.toFloat()).toInt()

    /**
     * A dsl feature which adds the view-height unit from HTML to screens.
     */
    inline val Number.vh
        get() = round(size.height / 100 * this.toFloat()).toInt()

}