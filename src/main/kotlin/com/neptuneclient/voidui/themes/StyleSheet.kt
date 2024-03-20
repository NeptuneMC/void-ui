package com.neptuneclient.voidui.themes

import com.neptuneclient.voidui.widgets.Element
import java.awt.Color

/**
 * Holds values for all style attributes in an element.
 *
 * @param color The only attribute that every element has is a color.
 */
abstract class StyleSheet(
    val color: Color
)