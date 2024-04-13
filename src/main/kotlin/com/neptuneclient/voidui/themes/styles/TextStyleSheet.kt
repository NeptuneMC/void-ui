package com.neptuneclient.voidui.themes.styles

import com.neptuneclient.voidui.themes.StyleSheet
import java.awt.Color
import java.nio.file.Path

class TextStyleSheet(
    color: Color,
    val font: Path,
    val size: Int,
    val letterSpacing: Int = 0,
    val underline: Boolean = false
) : StyleSheet(color)