package com.neptuneclient.voidui.objects

data class CornerRadius(
    val topLeft: Float,
    val topRight: Float,
    val bottomRight: Float,
    val bottomLeft: Float,
) {

    companion object {
        fun all(value: Float) = CornerRadius(value, value, value, value)
    }

    fun isEmpty() = (topLeft + topRight + bottomLeft + bottomRight) == 0.0f

}