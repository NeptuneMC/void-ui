package com.neptuneclient.voidui.ui

/**
 * @see Component
 */
abstract class StaticComponent(
    x: Int? = null,
    y: Int? = null,
    width: Int? = null,
    height: Int? = null,

    margin: Int? = null,
    padding: Int? = null,

    children: MutableList<Drawable>? = null
) : Component(x, y, width, height, margin, padding, children)