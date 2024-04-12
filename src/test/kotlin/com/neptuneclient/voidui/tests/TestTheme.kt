package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.themes.Styles
import com.neptuneclient.voidui.themes.Theme
import com.neptuneclient.voidui.themes.objects.Border
import com.neptuneclient.voidui.themes.styles.ImageStyleSheet
import com.neptuneclient.voidui.themes.styles.LinkStyleSheet
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.themes.styles.TextStyleSheet
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import java.awt.Color
import java.nio.file.Path

class TestTheme : Theme(
    backgroundPanel = Styles(
        normal = PanelStyleSheet(
            color = Color(0x101216),
            radius = 10
        )
    ),
    accentBackgroundPanel = Styles(
        normal = PanelStyleSheet(
            color = Color(26, 31, 41, 220),
            radius = 10
        )
    ),
    panel = Styles(
        normal = PanelStyleSheet(
            color = Color(0x272935),
            radius = 10,
            border = Border(
                sides = EdgeInsets.all(1f),
                color = Color(255, 255, 255, 40)
            )
        )
    ),
    accentPanel = Styles(
        normal = PanelStyleSheet(
            color = Color(0),
            radius = 10,
            border = Border(
                sides = EdgeInsets.all(1f),
                color = Color(255, 255, 255, 40)
            )
        )
    ),
    title = Styles(
        normal = TextStyleSheet(
            color = Color.WHITE,
            font = Path.of("fonts/Jost-SemiBold.ttf"),
            size = 32,
            letterSpacing = 12
        )
    ),
    smallTitle = Styles(
        normal = TextStyleSheet(
            color = Color.WHITE,
            font = Path.of("fonts/Jost-SemiBold.ttf"),
            size = 20,
            letterSpacing = 8
        )
    ),
    heading = Styles(
        normal = TextStyleSheet(
            color = Color.WHITE,
            font = Path.of("fonts/WorkSans-SemiBold.ttf"),
            size = 32
        )
    ),
    smallHeading = Styles(
        normal = TextStyleSheet(
            color = Color.WHITE,
            font = Path.of("fonts/WorkSans-SemiBold.ttf"),
            size = 20
        )
    ),
    text = Styles(
        normal = TextStyleSheet(
            color = Color.WHITE,
            font = Path.of("fonts/WorkSans-Regular.ttf"),
            size = 16
        )
    ),
    smallText = Styles(
        normal = TextStyleSheet(
            color = Color(255, 255, 255, 150),
            font = Path.of("fonts/WorkSans-Regular.ttf"),
            size = 13
        )
    ),
    link = Styles(
        normal = LinkStyleSheet(
            color = Color(0x3C6DFC),
            font = Path.of("fonts/WorkSans-Regular.ttf"),
            size = 16
        )
    ),
    image = Styles(
        normal = ImageStyleSheet(
            borderRadius = 0
        )
    )
)