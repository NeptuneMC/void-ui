package com.neptuneclient.voidui.units

import com.neptuneclient.voidui.widgets.Screen
import com.neptuneclient.voidui.widgets.Text
import kotlin.math.max
import kotlin.math.min

/**
 * A unit which represents distance on the canvas.
 *
 * @param value The value of the unit.
 */
abstract class LengthUnit(value: Float) : Unit<Float>(value) {

    abstract fun getPixels(screen: Screen, constrainedValue: Float): Float

}

/**
 * Represents exact pixels on the screen.
 *
 * @param value The amount of pixels.
 */
class PixelsUnit(value: Int) : LengthUnit(value.toFloat()) {
    override fun getPixels(screen: Screen, constrainedValue: Float) = max(0F, value)
}

/**
 * Represents a fraction of the parent widget.
 *
 * @param value The amount of percent.
 */
class PercentUnit(value: Float) : LengthUnit(value) {
    override fun getPixels(screen: Screen, constrainedValue: Float): Float {
        val value = max(0F, min(value, 100F))   // sets the value between 0 and 100
        return (value / 100F) * constrainedValue
    }
}

/**
 * Represents the fonts size of the [Text] element in the current theme.
 *
 * @param value The multiplier of the font size, e.g. ``value = 2`` means two times the font size.
 */
class FontSizeUnit(value: Float) : LengthUnit(value) {
    override fun getPixels(screen: Screen, constrainedValue: Float): Float {
        val textStyle = screen.void.theme.getStyles(Text::class)
        return (max(0F, value) / 100F) * textStyle.normal.size
    }
}

/**
 * Represents 1% from the screen's width.
 */
class ViewWidthUnit(value: Float) : LengthUnit(value) {
    override fun getPixels(screen: Screen, constrainedValue: Float): Float {
        val value = max(0F, min(value, 100F))
        return (value / 100F) * screen.size.width
    }
}

/**
 * Represents 1% from the screen's width.
 */
class ViewHeightUnit(value: Float) : LengthUnit(value) {
    override fun getPixels(screen: Screen, constrainedValue: Float): Float {
        val value = max(0F, min(value, 100F))
        return (value / 100F) * screen.size.height
    }
}
