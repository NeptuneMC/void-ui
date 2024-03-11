package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.VoidUI

/**
 * The base class for anything which represents an object in the UI.
 */
abstract class Drawable(
    var x: Int? = null,
    var y: Int? = null,
    var width: Int? = null,
    var height: Int? = null,

    var margin: Int? = null,
    var padding: Int? = null
) {

    /**
     * This is set in [Screen] right before the component tree is built.
     * TODO set this
     */
    lateinit var void: VoidUI

}