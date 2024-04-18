package com.neptuneclient.voidui.theme

import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.utils.Font
import java.awt.Color

/**
 * Used in [Theme] to define default styles for some widgets.
 */
data class DefaultStyles(
    val regularText: TextStyle,
    val smallText: TextStyle = regularText,
    val mediumText: TextStyle = regularText,
    val largeText: TextStyle = regularText,

    val image: ImageStyle
)

class ImageStyle(
    val cornerRadius: CornerRadius,
)

data class TextStyle(
    val font: Font,
    val color: Color,
    val size: Int,
    val letterSpacing: Float
)