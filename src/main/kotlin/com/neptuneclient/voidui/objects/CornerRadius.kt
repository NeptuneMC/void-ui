package com.neptuneclient.voidui.objects

/**
 * Holds values for corner radius on all four sides.
 */
data class CornerRadius(
    val topLeft: Float,
    val topRight: Float,
    val bottomRight: Float,
    val bottomLeft: Float,
) {

    companion object {
        /**
         * Returns a corner radius object which is the same on all sides.
         */
        @JvmStatic
        fun all(value: Float) = CornerRadius(value, value, value, value)
    }

    /**
     * Whether all sides of the corner radius is zero.
     */
    fun isEmpty() = (topLeft + topRight + bottomLeft + bottomRight) == 0.0f

}