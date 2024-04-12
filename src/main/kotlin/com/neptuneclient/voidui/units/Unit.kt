package com.neptuneclient.voidui.units

/**
 * Base class for custom units within the library. These units wrap other data types and resolve them at some point.
 *
 * A good example for this is [PercentUnit], at its core it is a float value, but it can only be converted into pixels
 * when the widget is sized in its [com.neptuneclient.voidui.widgets.Widget.layout] function, also with custom units
 * we can apply range constraints to these values, so a percent value never goes beyond 0 or 100.
 *
 * @param T The data type which represents the unit, usually some number type.
 * @param value The value of the unit.
 */
sealed class Unit<T>(val value: T)