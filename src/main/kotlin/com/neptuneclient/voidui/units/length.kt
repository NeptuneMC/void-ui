package com.neptuneclient.voidui.units

import com.neptuneclient.voidui.widgets.Screen

abstract class LengthUnit(value: Float) : Unit<Float>(value) {

    abstract fun getPixels(screen: Screen, constrainedValue: Float): Float

}

class PixelsUnit(value: Int) : LengthUnit(value.toFloat()) {
    override fun getPixels(screen: Screen, constrainedValue: Float) = value
}
class PercentUnit(value: Double) : LengthUnit(value.toFloat()) {
    override fun getPixels(screen: Screen, constrainedValue: Float) = (value / 100F) * constrainedValue
}