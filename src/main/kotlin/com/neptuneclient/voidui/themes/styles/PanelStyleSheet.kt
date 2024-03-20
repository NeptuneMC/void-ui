package com.neptuneclient.voidui.themes.styles

import com.neptuneclient.voidui.themes.StyleSheet
import com.neptuneclient.voidui.themes.objects.Border
import com.neptuneclient.voidui.themes.objects.DropShadow
import java.awt.Color

class PanelStyleSheet(
    color: Color,
    val radius: Int = 0,
    val border: Border? = null,
    val shadow: DropShadow? = null,
) : StyleSheet(color)