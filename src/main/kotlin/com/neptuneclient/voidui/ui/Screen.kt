package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.VoidUI

abstract class Screen(private val void: VoidUI) {

    var width = 0
    var height = 0

    abstract fun build(): Component

    val Number.vw
        get() = (this.toDouble() / 100) * width

    val Number.vh
        get() = (this.toDouble() / 100) * height

}