package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.ui.objects.EdgeInsets

/**
 * The base class for anything which represents an object in the UI.
 */
abstract class Drawable(
    var x: Int? = null,
    var y: Int? = null,
    var width: Int? = null,
    var height: Int? = null,

    var margin: EdgeInsets = EdgeInsets.zero,
    var padding: EdgeInsets = EdgeInsets.zero
) {

    /**
     * This is set in [Screen] right before the component tree is built.
     * TODO set this
     */
    lateinit var void: VoidUI

    abstract fun render()

    /*companion object {
        operator fun get(vararg children: Drawable): MutableList<Drawable> {
            return children.toMutableList()
        }
    }*/

}