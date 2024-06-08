package com.neptuneclient.voidui.framework

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A property delegation which rebuilds the widget tree once its value changes.
 *
 * @param initialValue The initial value of the state property.
 * @param onStateChange The action which is run when the value changes. See [Widget.state] for more info.
 */
class State<T>(initialValue: T, private val onStateChange: (T) -> Unit) : ReadWriteProperty<Any?, T> {

    /**
     * Holds the actual value of the property.
     */
    var value: T = initialValue
        private set

    override fun getValue(thisRef: Any?, property: KProperty<*>) = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        onStateChange(value)
    }

}