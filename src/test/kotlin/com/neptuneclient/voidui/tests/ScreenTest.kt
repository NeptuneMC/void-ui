@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.EventHandler
import com.neptuneclient.voidui.event.TestEvent
import com.neptuneclient.voidui.widgets.*
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW

class PaddingPanel(private val padding: EdgeInsets = EdgeInsets.zero, private val child: Widget) : Widget() {

    override fun build(): Widget {
        return Panel(
            Padding(
                child,
                padding
            )
        )
    }

}

class TestScreen(void: VoidUI) : Screen(void) {

    private var counter by state(5)

    override fun build(): Widget {
        return BackgroundPanel(
            Padding(
                padding = EdgeInsets.all(20F),
                child = PaddingPanel(
                    padding = EdgeInsets.symmetric(20F, 40F),
                    child = Text("Hello")
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