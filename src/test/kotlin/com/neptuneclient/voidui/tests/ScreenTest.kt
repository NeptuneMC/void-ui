@file:DisabledIfEnvironmentVariable(named = "CI", matches = "\\w+")

package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.TestEvent
import com.neptuneclient.voidui.widgets.*
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import org.junit.jupiter.api.condition.DisabledIfEnvironmentVariable
import org.lwjgl.glfw.GLFW
import java.net.URI

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
        return Panel(
            width = 50.percent,
            height = 200.px,
            child = Padding(
                padding = EdgeInsets.all(20F),
                child = Row(
                    arrayOf(
                        Text("Check out my  "),
                        Link(
                            label = "link  ",
                            address = URI("http://localhost")
                        ),
                        Text("also this is the  "),
                        Link(
                            label = "Neptune Webiste",
                            address = URI("https://neptuneclient.com/")
                        ),
                        Text(".")
                    )
                )
            )
        )
    }

}

val voidUI = VoidUI(TestRenderer(), TestTheme())

fun main() {
    voidUI.eventHandler.register(TestEvent::class, "ok") {  // normally you would use `this` as a proper key
        println(it.test)
    }

    val screen = TestScreen(voidUI)
    screen.init()

    val renderer = voidUI.renderer as TestRenderer
    while (!GLFW.glfwWindowShouldClose(renderer.window)) {
        screen.render()
        //TestEvent("lululu").call(voidUI)
    }

    voidUI.eventHandler.unregister("ok")
    
    renderer.destroy()
}