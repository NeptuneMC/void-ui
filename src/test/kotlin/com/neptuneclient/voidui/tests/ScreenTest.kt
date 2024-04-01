@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.EventHandler
import com.neptuneclient.voidui.event.TestEvent
import com.neptuneclient.voidui.widgets.*
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW

class TestScreen(void: VoidUI) : Screen(void) {

    private var counter by state(5)

    override fun build(): Widget {
        return BackgroundPanel(
            child = Padding(
                padding = EdgeInsets.all(40F),
                child = Column(
                    gap = 20,
                    children = arrayOf(
                        Text("Title"),
                        Column(
                            gap = 10,
                            children = arrayOf(
                                Panel(
                                    child = Padding(
                                        padding = EdgeInsets.all(20F),
                                        child = Text("Body Text Woooo")
                                    )
                                ),
                                Panel(
                                    child = Padding(
                                        padding = EdgeInsets.all(20F),
                                        child = Row(
                                            gap = 20,
                                            children = arrayOf(
                                                Text("Hello"),
                                                Text("World")
                                            )
                                        )
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

val voidUI = VoidUI(TestRenderer(), TestTheme())

fun main() {
    val handler = EventHandler()
    handler.register(TestEvent::class, "ok") {  // normally you would use `this` as a proper key
        println(it.test)
    }

    val screen = TestScreen(voidUI)
    screen.init()

    val renderer = voidUI.renderer as TestRenderer
    while (!GLFW.glfwWindowShouldClose(renderer.window)) {
        screen.render()
        TestEvent("lululu").call(voidUI)
    }

    handler.unregister("ok")
    
    renderer.destroy()
}