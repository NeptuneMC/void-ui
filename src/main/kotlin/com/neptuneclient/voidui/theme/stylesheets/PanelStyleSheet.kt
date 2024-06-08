package com.neptuneclient.voidui.theme.stylesheets

import com.neptuneclient.voidui.objects.Border
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.theme.StyleSheet
import java.awt.Color

data class PanelStyleSheet(
    val color: Color,
    val border: Border = Border(0f, Color(0)),
    val cornerRadius: CornerRadius = CornerRadius.zero,
) : StyleSheet