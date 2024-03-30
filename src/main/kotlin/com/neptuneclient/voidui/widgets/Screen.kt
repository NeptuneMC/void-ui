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
     * The width of the render frame.
     */
    val width = void.renderer.windowWidth()
    /**
     * The height of the render frame.
     */
    val height = void.renderer.windowHeight()

    /**
     * The element stack of the screen.
     */
    val elementStack = ElementStack(void.renderer)

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
        recalcOffsets()
    }

    private fun layout(): Size {
        root.layout(BoxConstraints.loose(width.toFloat(), height.toFloat()))
        val offsetY = (height - root.size.height) / 2
        val offsetX = (width - root.size.width) / 2
        root.offset = Offset(offsetX, offsetY)
        return root.size
    }

    /**
     * VERY TEMP, UNTIL SOMETHING BETTER IS IMPLEMENTED
     */
    private fun recalcOffsets() {
        root.recalcOffsets()
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
        get() = round(width / 100 * this.toFloat()).toInt()

    /**
     * A dsl feature which adds the view-height unit from HTML to screens.
     */
    inline val Number.vh
        get() = round(height / 100 * this.toFloat()).toInt()

}