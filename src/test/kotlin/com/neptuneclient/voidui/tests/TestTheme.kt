package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.theme.DefaultStyles
import com.neptuneclient.voidui.theme.ImageStyle
import com.neptuneclient.voidui.theme.TextStyle
import com.neptuneclient.voidui.theme.Theme
import com.neptuneclient.voidui.utils.Font
import java.awt.Color
import java.nio.file.Path

class TestTheme : Theme(
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