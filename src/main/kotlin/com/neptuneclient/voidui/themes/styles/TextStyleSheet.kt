package com.neptuneclient.voidui.themes.styles

import com.neptuneclient.voidui.themes.StyleSheet
import java.awt.Color
import java.nio.file.Path

class TextStyleSheet(
    color: Color,
    val font: Path,
    val size: Int
) : StyleSheet(color)