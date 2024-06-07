package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.objects.Border
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.theme.*
import com.neptuneclient.voidui.theme.stylesheets.PanelStyleSheet
import com.neptuneclient.voidui.utils.Font
import java.awt.Color
import java.nio.file.Path

class TestTheme : Theme(
    panelStyles = Styles(
        regular = PanelStyleSheet(
            color = Color(39, 41, 53),
            border = Border(1f, Color(255, 255, 255, 38)),
            cornerRadius = CornerRadius.all(10f)
        ),
        hovered = PanelStyleSheet(
            color = Color(39, 41, 255),
            border = Border(1f, Color(255, 255, 255, 38)),
            cornerRadius = CornerRadius.all(10f)
        )
    ),
    defaultStyles = DefaultStyles(
        regularText = TextStyle(
            font = Font("WorkSans", Path.of("fonts/WorkSans-Regular.ttf")),
            size = 16,
            color = Color.WHITE,
            letterSpacing = 0f
        ),
        image = ImageStyle(
            cornerRadius = CornerRadius.zero
        )
    )
)