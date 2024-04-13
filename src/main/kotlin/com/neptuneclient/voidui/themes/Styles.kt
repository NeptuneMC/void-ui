package com.neptuneclient.voidui.themes

/**
 * This class keeps style sheets in different states for an element.
 *
 * @param T The type of the style sheet.
 * @param normal The normal state of the element.
 * @param hovered The styles for the elemenet when it is hovered.
 * @param active The style for the element when it is currently active, e.g. a button is pressed.
 */
data class Styles<T : StyleSheet>(val normal: T, val hovered: T = normal, val active: T = hovered) {
    
    enum class Type { NORMAL, HOVERED, ACTIVE }
    
}