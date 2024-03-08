package com.neptuneclient.voidui.ui.elements

import com.neptuneclient.voidui.ui.Drawable

/**
 * Elements are the core building blocks of the library. They can not be created by the user and do not have children.
 * Instead, they are rendered directly to the screen, and have also custom style properties (coming soon™).
 */
sealed class Element : Drawable {

    abstract fun render()

}