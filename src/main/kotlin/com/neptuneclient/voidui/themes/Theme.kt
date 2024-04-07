package com.neptuneclient.voidui.themes

import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TextStyleSheet
import com.neptuneclient.voidui.widgets.*
import java.lang.IllegalArgumentException
import kotlin.reflect.KClass

/**
 * This class has parameters for the style sheets of every element.
 */
open class Theme(
    backgroundPanel: Styles<PanelStyleSheet>,
    accentBackgroundPanel: Styles<PanelStyleSheet>,
    panel: Styles<PanelStyleSheet>,
    accentPanel: Styles<PanelStyleSheet>,

    title: Styles<TextStyleSheet>,
    smallTitle: Styles<TextStyleSheet>,
    heading: Styles<TextStyleSheet>,
    smallHeading: Styles<TextStyleSheet>,
    text: Styles<TextStyleSheet>,
    smallText: Styles<TextStyleSheet>
) {

    /**
     * A map which holds every element type and its adjacent styles.
     */
    private val styles = mapOf(
        BackgroundPanel::class to backgroundPanel,
        AccentBackgroundPanel::class to accentBackgroundPanel,
        Panel::class to panel,
        AccentPanel::class to accentPanel,

        Title::class to title,
        SmallTitle::class to smallTitle,
        Heading::class to heading,
        SmallHeading::class to smallHeading,
        Text::class to text,
        SmallText::class to smallText
    )

    /**
     * Returns the style sheet of the given element.
     *
     * @param element The element class.
     * @param type The type of style sheet, e.g. [Styles.Type.NORMAL], [Styles.Type.HOVERED].
     *
     * @return The style sheet of the given element.
     *
     * @throws IllegalArgumentException If the given element class has no style sheet in the registry map.
     */
    fun <T : StyleSheet> getStyleSheet(element: KClass<out Element<T>>, type: Styles.Type): T {
        for ((k, v) in styles) {
            if (k != element) continue
            return when (type) {
                Styles.Type.NORMAL -> v.normal
                Styles.Type.HOVERED -> v.hovered
                Styles.Type.ACTIVE -> v.active
            } as T
        }
        throw IllegalArgumentException("No stylesheet found for type: ${element.simpleName}")
    }
 
}