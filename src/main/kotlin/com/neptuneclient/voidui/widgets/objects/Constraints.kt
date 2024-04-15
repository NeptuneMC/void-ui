package com.neptuneclient.voidui.widgets.objects

/**
 * An immutable class which represents the constraints of an element.
 */
abstract class Constraints {
    abstract val isTight: Boolean
}

data class BoxConstraints(
    var minWidth: Float = 0f,
    var maxWidth: Float = Float.POSITIVE_INFINITY,
    var minHeight: Float = 0f,
    var maxHeight: Float = Float.POSITIVE_INFINITY,
) : Constraints() {
    companion object {
        fun tight(size: Size) = BoxConstraints(size.width, size.width, size.height, size.height)
        fun tight(width: Float?, height: Float?) = BoxConstraints(
            width ?: 0f,
            width ?: Float.POSITIVE_INFINITY,
            height ?: 0f,
            height ?: Float.POSITIVE_INFINITY
        )

        fun loose(size: Size) = BoxConstraints(0f, size.width, 0f, size.height)
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

    override val isTight
        get() = minWidth == maxWidth && minHeight == maxHeight

    val biggest get() = Size(maxWidth, maxHeight)
    val smallest get() = Size(minWidth, minHeight)

    fun constrain(size: Size) = Size(
        size.width.coerceIn(minWidth, maxWidth),
        size.height.coerceIn(minHeight, maxHeight)
    )

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

    fun isSatisfiedBy(size: Size) = size.width in minWidth..maxWidth && size.height in minHeight..maxHeight
}