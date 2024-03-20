package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.themes.Styles
import com.neptuneclient.voidui.themes.Theme
import com.neptuneclient.voidui.themes.objects.Border
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TextStyleSheet
import java.awt.Color
import java.nio.file.Path

class TestTheme : Theme(
    panel = Styles(
        normal = PanelStyleSheet(
            color = Color.BLUE,
            radius = 10
        ),
        hovered = PanelStyleSheet(
            color = Color(200, 200, 200),
            radius = 5,
            border = Border(
                width = 1.5F,
                color = Color.WHITE
            )
        )
    ),
    text = Styles(
        normal = TextStyleSheet(
            color = Color.YELLOW,
            font = Path.of("fonts/WorkSans-Regular.ttf"),
            size = 20
        )
    )
)