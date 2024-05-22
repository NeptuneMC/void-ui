package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.MouseClickedEvent
import com.neptuneclient.voidui.framework.Screen
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.objects.EdgeInsets
import com.neptuneclient.voidui.utils.image
import com.neptuneclient.voidui.widgets.*
import java.awt.Color

class MainMenu(voidUI: VoidUI) : Screen(voidUI) {

    private var counter by stateOf(0)

    init {
        registerEventAction(MouseClickedEvent::class) {
            counter++
        }
    }

    override fun build(): Widget {
        return Stack(
            children = arrayOf(
                Image(
                    src = image("images/main_menu.png"),
                    fit = ImageFit.COVER
                ),
                Container(
                    width = width.toFloat(),
                    height = height.toFloat(),
                    color = Color(0, 0, 0, 100),
                    child = Padding(
                        padding = EdgeInsets.all(10f),
                        child = Column(
                            children = arrayOf(
                                AccountSwitcher(),
                                Center(
                                    Container(
                                        cornerRadius = CornerRadius.all(10f),
                                        color = Color(10, 12, 20),
                                        padding = EdgeInsets.all(20f),
                                        child = Column(
                                            gap = 20f,
                                            children = arrayOf(
                                                Button("Hello"),
                                                Button("World"),
                                                Text("Counter $counter")
                                            )
                                        )
                                    )
                                ),
                                Row(
                                    children = arrayOf(
                                        Text("Minecraft 1.8.9")
                                    )
                                )
                            )
                        )
                    )
                )
            )
        )
    }

}

fun AccountSwitcher(): Widget {
    return Container(
        width = 250f,
        cornerRadius = CornerRadius.all(10f),
        color = Color(10, 12, 20),
        padding = EdgeInsets.all(20f),

        child = Row(
            gap = 10f,
            children = arrayOf(
                Image(
                    src = image("images/head.png"),
                    imageSize = Size(16f, 16f),
                    cornerRadius = CornerRadius.all(5f)
                ),
                Text("marc_daddy")
            )
        )
    )
}

fun Button(text: String): Widget {
    return Container(
        width = 250f,
        height = 40f,
        cornerRadius = CornerRadius.all(10f),
        color = Color(100, 70, 200),

        child = Center(
            Text(text)
        )
    )
}