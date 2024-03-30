package com.neptuneclient.voidui.widgets.objects

open class SizedBase(
    val dx: Float,
    val dy: Float,
)


data class Size(
    val width: Float,
    val height: Float,
) : SizedBase(width, height) {
    companion object {
        val zero = Size(0f, 0f)
    }

    val isNotEmpty get() = width > 0 && height > 0

    operator fun plus(other: Size) = Size(width + other.width, height + other.height)
    operator fun minus(other: Size) = Size(width - other.width, height - other.height)
    operator fun times(other: Size) = Size(width * other.width, height * other.height)
    operator fun div(other: Size) = Size(width / other.width, height / other.height)
}

data class Offset(
    val x: Float,
    val y: Float,
) : SizedBase(x, y) {
    companion object {
        val zero = Offset(0f, 0f)
    }

    operator fun plus(other: Offset) = Offset(dx + other.dx, dy + other.dy)
    operator fun minus(other: Offset) = Offset(dx - other.dx, dy - other.dy)
    operator fun times(other: Offset) = Offset(dx * other.dx, dy * other.dy)
    operator fun div(other: Offset) = Offset(dx / other.dx, dy / other.dy)
}
