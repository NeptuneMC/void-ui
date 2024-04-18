package com.neptuneclient.voidui.framework

import com.neptuneclient.voidui.objects.EdgeInsets

/**
 * Describes the bounds of a widget.
 *
 * It contains two boxes, a minimum size of the widget and a maximum size.
 */
data class BoxConstraints(
    val minWidth: Float = 0f,
    val maxWidth: Float = Float.POSITIVE_INFINITY,
    val minHeight: Float = 0f,
    val maxHeight: Float = Float.POSITIVE_INFINITY,
) {
    companion object {
        /**
         * Sets both the minimum and maximum size of the constraints.
         *
         * @param size The size of the constraints.
         */
        fun tight(size: Size) = BoxConstraints(size.width, size.width, size.height, size.height)

        /**
         * Sets both the minimum and maximum size of the constraints.
         *
         * @param width The optional width of the constraints.
         * @param height The optional height of the constraints.
         */
        fun tight(width: Float?, height: Float?) = BoxConstraints(
            width ?: 0f,
            width ?: Float.POSITIVE_INFINITY,
            height ?: 0f,
            height ?: Float.POSITIVE_INFINITY
        )

        /**
         * Sets only the maximum size of the constraints.
         *
         * @param size The maximum size.
         */
        fun loose(size: Size) = BoxConstraints(0f, size.width, 0f, size.height)

        /**
         * Sets only the maximum size of the constraints.
         *
         * @param width The optional maximum width of the constraints.
         * @param height The optional maximum height of the constraints.
         */
        fun loose(width: Float?, height: Float?) = BoxConstraints(
            0f,
            width ?: Float.POSITIVE_INFINITY,
            0f,
            height ?: Float.POSITIVE_INFINITY
        )

        fun expand(width: Float?, height: Float?) = BoxConstraints(
            width ?: Float.POSITIVE_INFINITY,
            width ?: Float.POSITIVE_INFINITY,
            height ?: Float.POSITIVE_INFINITY,
            height ?: Float.POSITIVE_INFINITY
        )
    }

    /**
     * Whether the minimum size and the maximum size are the same.
     */
    val isTight
        get() = minWidth == maxWidth && minHeight == maxHeight

    /**
     * The biggest possible size within the constraints.
     */
    val biggest get() = Size(maxWidth, maxHeight)
    /**
     * The smallest possible size within the constraints.
     */
    val smallest get() = Size(minWidth, minHeight)

    /**
     * Adjusts the given size object to fit into the constraints.
     */
    fun constrain(size: Size) = Size(
        size.width.coerceIn(minWidth, maxWidth),
        size.height.coerceIn(minHeight, maxHeight)
    )

    /**
     * Subtract the amount of padding from each side of both constraint boxes.
     *
     * @param padding The given padding.
     */
    fun deflate(padding: EdgeInsets) = BoxConstraints(
        (minWidth - padding.horizontal).coerceAtLeast(0f),
        (maxWidth - padding.horizontal).coerceAtLeast(0f),
        (minHeight - padding.vertical).coerceAtLeast(0f),
        (maxHeight - padding.vertical).coerceAtLeast(0f)
    )

    fun enforce(constraints: BoxConstraints) = BoxConstraints(
        minWidth.coerceIn(constraints.minWidth, constraints.maxWidth),
        maxWidth.coerceIn(constraints.minWidth, constraints.maxWidth),
        minHeight.coerceIn(constraints.minHeight, constraints.maxHeight),
        maxHeight.coerceIn(constraints.minHeight, constraints.maxHeight)
    )

    /**
     * Whether the given size object satisfies the constraints.
     */
    fun isSatisfiedBy(size: Size) = size.width in minWidth..maxWidth && size.height in minHeight..maxHeight
}