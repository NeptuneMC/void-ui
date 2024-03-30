package com.neptuneclient.voidui.themes.objects

import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import java.awt.Color

/**
 * Holds values which describe a border on an element.
 *
 * @param sides The width of each of the sides of the border.
 * @param color The color of the border.
 */
data class Border(val sides: EdgeInsets, val color: Color)