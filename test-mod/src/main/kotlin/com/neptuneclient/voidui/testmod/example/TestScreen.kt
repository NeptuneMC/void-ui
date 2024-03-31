package com.neptuneclient.voidui.testmod.example

import com.neptuneclient.voidui.testmod.ModEntry
import com.neptuneclient.voidui.widgets.*
import com.neptuneclient.voidui.widgets.objects.EdgeInsets

class TestScreen : Screen(ModEntry.voidUI) {

    override fun build(): Widget {
        return BackgroundPanel(
            child = Padding(
                padding = EdgeInsets.all(20F),
                child = Column(
                    gap = 10,
                    children = arrayOf(
                        Text("Hello World"),
                        Text("Heh")
                    )
                )
            )
        )
    }

}