package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.ui.objects.EdgeInsets
import kotlin.properties.Delegates

/**
 * @see Component
 */
abstract class ReactiveComponent(
    x: Int? = null,
    y: Int? = null,
    width: Int? = null,
    height: Int? = null,

    margin: EdgeInsets = EdgeInsets.zero,
    padding: EdgeInsets = EdgeInsets.zero,

    children: MutableList<Drawable>? = null
) : Component(x, y, width, height, margin, padding, children) {

    /**
     * Used to initialize a stateful variable. Whenever a stateful variable changes, the component gets rebuild.
     *
     * @param initialValue The initial value of the variable
     *
     * @sample ``var testState by state(2)`` - Defines a stateful variable with an initial value of 2.
     */
    protected fun <T> state(initialValue: T) = Delegates.observable(initialValue) { _, _, new ->
        println("gndfjldsfs: $new")
        this.build()
        // TODO handle component rebuilding properly
    }

}