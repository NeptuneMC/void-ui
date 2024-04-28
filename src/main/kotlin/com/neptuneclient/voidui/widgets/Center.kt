package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.Widget

/**
 * Centers a child widget in the parents maximum constraints.
 *
 * Because this is a function it doesn't build its own widget but rather acts as an alias to ``Align(child, Alignment.center``.
 *
 * @param child The child widget which will be centered.
 */
fun Center(child: Widget) = Align(child, Alignment.center)