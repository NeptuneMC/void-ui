package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.ui.elements.Element

/**
 * For now this is just so [Component] and [Element] have the same superclass.
 */
abstract class Drawable {

    /**
     * This is set in [Screen] right before the component tree is built.
     * TODO set this
     */
    lateinit var void: VoidUI

}