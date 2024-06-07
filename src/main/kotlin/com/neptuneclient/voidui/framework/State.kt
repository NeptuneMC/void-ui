package com.neptuneclient.voidui.framework

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * A property delegation which rebuilds the widget tree once its value changes.
 *
 * @param value The initial value of the state property.
 * @param onStateChange The action which is run when the value changes. See [Widget.state] for more info.
 */
class State<T>(private var value: T, private val onStateChange: (T) -> Unit) : ReadWriteProperty<Any?, T> {

    override fun getValue(thisRef: Any?, property: KProperty<*>) = value

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        this.value = value
        onStateChange(value)
    }

}