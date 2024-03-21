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
            color = Color(0x272935),
            radius = 10,
            border = Border(
                width = 1F,
                color = Color(255, 255, 255, 40)
            )
        )
    ),
    text = Styles(
        normal = TextStyleSheet(
            color = Color.WHITE,
            font = Path.of("fonts/WorkSans-Regular.ttf"),
            size = 16
        )
    )
)