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
    x: Int? = null,
    y: Int? = null,
    width: Int? = null,
    height: Int? = null,

    margin: Int? = null,
    padding: Int? = null,

    val children: MutableList<Drawable>? = null
) : Drawable(x, y, width, height, margin, padding) {

    abstract fun build(): Drawable

}