package com.neptuneclient.voidui.units

data class EdgeInsets(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float,
) {
    companion object {
        @JvmStatic
        fun all(value: Float) = EdgeInsets(value, value, value, value)
        @JvmStatic
        fun only(
            left: Float = 0f,
            top: Float = 0f,
            right: Float = 0f,
            bottom: Float = 0f
        ) = EdgeInsets(left, top, right, bottom)
        @JvmStatic
        fun symmetric(
            vertical: Float = 0f,
            horizontal: Float = 0f
        ) = EdgeInsets(horizontal, vertical, horizontal, vertical)
        val zero = EdgeInsets(0f, 0f, 0f, 0f)
    }

    val horizontal get() = left + right
    val vertical get() = top + bottom

    operator fun plus(other: EdgeInsets) = EdgeInsets(
        left + other.left,
        top + other.top,
        right + other.right,
        bottom + other.bottom
    )

    operator fun minus(other: EdgeInsets) = EdgeInsets(
        left - other.left,
        top - other.top,
        right - other.right,
        bottom - other.bottom
    )

    operator fun times(other: EdgeInsets) = EdgeInsets(
        left * other.left,
        top * other.top,
        right * other.right,
        bottom * other.bottom
    )

    operator fun div(other: EdgeInsets) = EdgeInsets(
        left / other.left,
        top / other.top,
        right / other.right,
        bottom / other.bottom
    )
}