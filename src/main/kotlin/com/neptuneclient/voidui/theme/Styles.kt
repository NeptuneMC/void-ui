package com.neptuneclient.voidui.theme

/**
 * A data class which holds multiple stylesheets in different states.
 *
 * @param regular The regular style of the widget.
 * @param hovered The style when the widget is hovered.
 * @param active The style when the widget is clicked.
 *
 * TODO add transitions
 */
data class Styles<T : StyleSheet>(
    val regular: T,
    val hovered: T = regular,
    val active: T = hovered
)