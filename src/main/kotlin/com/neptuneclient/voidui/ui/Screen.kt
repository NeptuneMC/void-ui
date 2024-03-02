package com.neptuneclient.voidui.ui

abstract class Screen {

    var width = 0
    var height = 0

    abstract fun build(): Component

    val Number.vw
        get() = (this.toDouble() / 100) * width

    val Number.vh
        get() = (this.toDouble() / 100) * height

}