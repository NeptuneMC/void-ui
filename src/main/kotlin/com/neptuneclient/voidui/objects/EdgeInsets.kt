package com.neptuneclient.voidui.objects

import com.neptuneclient.voidui.framework.Offset

/**
 * Holds values for insets of a rect on all four sides.
 */
data class EdgeInsets(
    val left: Float,
    val top: Float,
    val right: Float,
    val bottom: Float,
) {
    companion object {
        /**
         * Creates an edge insets object where each side is the same.
         */
        @JvmStatic
        fun all(value: Float) = EdgeInsets(value, value, value, value)

        /**
         * Lets you specify each side individually.
         */
        @JvmStatic
        fun only(
            left: Float = 0f,
            top: Float = 0f,
            right: Float = 0f,
            bottom: Float = 0f
        ) = EdgeInsets(left, top, right, bottom)

        /**
         * Creates an object where the vertical and horizontal insets are the same.
         */
        @JvmStatic
        fun symmetric(
            vertical: Float = 0f,
            horizontal: Float = 0f
        ) = EdgeInsets(horizontal, vertical, horizontal, vertical)
        val zero = EdgeInsets(0f, 0f, 0f, 0f)
    }

    /**
     * The inset of both horizontal sides combined.
     */
    val horizontal get() = left + right

    /**
     * The inset of both vertical sides combined.
     */
    val vertical get() = top + bottom

    val topLeft get() = Offset(left, top)
    val topRight get() = Offset(right, top)
    val bottomLeft get() = Offset(left, bottom)
    val bottomRight get() = Offset(right, bottom)

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