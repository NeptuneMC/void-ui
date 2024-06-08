package com.neptuneclient.voidui.theme

import com.neptuneclient.voidui.theme.stylesheets.PanelStyleSheet
import com.neptuneclient.voidui.ui.Panel
import kotlin.reflect.KClass

/**
 * Holds values which define the general style of the whole library.
 */
abstract class Theme(
    val defaultStyles: DefaultStyles,

    panelStyles: Styles<PanelStyleSheet>
) {

    /**
     * A map which holds every styled widget type and its adjacent styles.
     */
    private val styles = mapOf(
        panelStyles to Panel::class
    )

    /**
     * Returns the styles of the given styled widget.
     *
     * @param widget The widget class.
     *
     * @return The styles of the given element.
     *
     * @throws IllegalArgumentException If the given widget class has no style sheet in the registry map.
     */
    fun <T : StyleSheet> getStyles(widget: KClass<out StyledWidget<T>>): Styles<T> {
        for ((k, v) in styles) {
            if (v != widget) continue
            return k as Styles<T>
        }
        throw IllegalArgumentException("No style sheet found for type: ${widget.simpleName}")
    }

}