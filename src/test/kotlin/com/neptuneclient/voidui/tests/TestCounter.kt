package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.events.MouseClickedEvent
import com.neptuneclient.voidui.event.events.RenderTickEvent
import com.neptuneclient.voidui.framework.Screen
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.objects.EdgeInsets
import com.neptuneclient.voidui.utils.image
import com.neptuneclient.voidui.widgets.*
import java.awt.Color

class TestCounter(voidUI: VoidUI) : Screen(voidUI) {

    private var counter by state(0)
    private var clicks by state(mutableListOf<Long>())

    init {
        registerEventAction(RenderTickEvent::class) {
            clicks.removeIf { it + 1000 < System.currentTimeMillis() }
            clicks = clicks
        }
    }

    override fun build(): Widget {
        return Column(
            gap = 10f,
            children = arrayOf(
                Container(
                    color =  Color(0x272935),
                    cornerRadius = CornerRadius.all(20f),
                    padding = EdgeInsets.all(20f),
                    width = 600f,
                    child = Row(
                        gap = 40f,
                        children = arrayOf(
                            Button(child = Image(
                                src = image("images/cross.png"),
                                imageSize = Size(16f, 16f)
                            )) {
                                counter = 0
                            },
                            Button(child = Text("Click Me")) {
                                counter++
                            },
                            Padding(
                                padding = EdgeInsets.symmetric(vertical = 10f),
                                child = Text("Current Counter: $counter")
                            )
                        )
                    )
                ),
                Container(
                    color =  Color(0x272935),
                    cornerRadius = CornerRadius.all(20f),
                    padding = EdgeInsets.all(20f),
                    width = 600f,
                    child = Button(child = Text("${clicks.size} CPS")) {
                        clicks.add(System.currentTimeMillis())
                        clicks = clicks
                    }
                )
            )
        )
    }

}

class Button(val child: Widget, val action: () -> Unit) : Widget() {

    override fun init(screen: Screen, parent: Widget) {
        super.init(screen, parent)
        registerEventAction(MouseClickedEvent::class) {
            if (this.hovered())
                action()
        }
    }

    override fun build(): Widget {
        return Container(
            color = Color(0x3C6DFC),
            cornerRadius = CornerRadius.all(10f),
            padding = EdgeInsets.symmetric(vertical = 10f, horizontal = 20f),
            child = child
        )
    }

}