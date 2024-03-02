package com.neptuneclient.voidui.ui

sealed class Component(
    var x: Int? = null,
    var y: Int? = null,
    width: Int? = null,
    height: Int? = null
) {

    var width = width ?: 0
    var height = height ?: 0

    abstract fun build(): Component

    val Number.cw
        get() = (this.toDouble() / 100) * width

    val Number.ch
        get() = (this.toDouble() / 100) * height

}