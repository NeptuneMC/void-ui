package com.neptuneclient.voidui.ui

/**
 * Components are a small reusable chunk of drawables. Syntactically they are very similar to screens, but instead of being
 * displayed on the screen, you can use them just like elements within other parts of your UI.
 *
 * There are two types of components:
 * * [StaticComponent]: It is rendered once when the screen is built and is used to display static information.
 * * [ReactiveComponent]: It can hold variables which will, when changed, will rebuild the component.
 */
sealed class Component(
    var x: Int? = null,
    var y: Int? = null,
    var width: Int? = null,
    var height: Int? = null
) : Drawable {
    abstract fun build(): Drawable
}