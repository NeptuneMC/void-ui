package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.VoidUI

abstract class Screen(private val void: VoidUI) {

    abstract fun build(): Component

}