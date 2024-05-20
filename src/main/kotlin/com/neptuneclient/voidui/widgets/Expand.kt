package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.Widget

/**
 * A widget which expands to the maximum constraints of the parent widget.
 *
 * @param child The child widget.
 */
fun Expand(child: Widget) = FractionallySizedBox(child, 1.0, 1.0)