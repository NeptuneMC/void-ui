package com.neptuneclient.voidui.themes.styles

import com.neptuneclient.voidui.themes.StyleSheet
import java.awt.Color
import java.nio.file.Path

class LinkStyleSheet(
    color: Color,
    val font: Path,
    val size: Int,
    val letterSpacing: Int = 0
) : StyleSheet(color)