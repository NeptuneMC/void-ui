package com.neptuneclient.voidui.framework

/**
 * Represents the size of a widget.
 *
 * @param width The width of the widget.
 * @param height The width of the height.
 */
data class Size(val width: Float, val height: Float) {
    companion object {
        val zero = Size(0f, 0f)
    }

    val isNotEmpty get() = width > 0 && height > 0

    /**
     * Converts the size object into an offset with the same values.
     */
    fun toOffset() = Offset(width, height)

    operator fun plus(other: Size) = Size(width + other.width, height + other.height)
    operator fun minus(other: Size) = Size(width - other.width, height - other.height)
    operator fun times(other: Size) = Size(width * other.width, height * other.height)
    operator fun div(other: Size) = Size(width / other.width, height / other.height)
}

/**
 * Represents the offset of a widget from the screen origin.
 */
data class Offset(val x: Float, val y: Float) {
    companion object {
        val zero = Offset(0f, 0f)
    }

    /**
     * Converts the offset into a size with the same values.
     */
    fun toSize() = Size(x, y)

    operator fun plus(other: Offset) = Offset(x + other.x, y + other.y)
    operator fun minus(other: Offset) = Offset(x - other.x, y - other.y)
    operator fun times(other: Offset) = Offset(x * other.x, y * other.y)
    operator fun div(other: Offset) = Offset(x / other.x, y / other.x)

}
