package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.ui.objects.EdgeInsets

/**
 * @see Component
 */
abstract class StaticComponent(
    x: Int? = null,
    y: Int? = null,
    width: Int? = null,
    height: Int? = null,

    margin: EdgeInsets = EdgeInsets.zero,
    padding: EdgeInsets = EdgeInsets.zero,

    children: MutableList<Drawable>? = null
) : Component(x, y, width, height, margin, padding, children)