package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.ui.Component
import com.neptuneclient.voidui.ui.ReactiveComponent
import com.neptuneclient.voidui.ui.Screen
import com.neptuneclient.voidui.ui.State
import org.junit.jupiter.api.Test

class VoidScreenTest(void: VoidUI) : Screen(void) {
    override fun build(): Component {
        return TestComponent()
    }
}

class TestComponent : ReactiveComponent() {
    @State
    public var testState = 5

    override fun build(): Component {
        println("Building TestComponent")
        return this
    }
}

object Tests {
    @Test
    fun testState() {
        val renderer = MockRenderer()
        val void = VoidUI(renderer)
        val screen = VoidScreenTest(void)

        val screen_component = screen.build()
        val component = screen_component.build()
        if (component is TestComponent) {
            println("TestComponent.testState: ${component.testState}")
            component.testState = 1
            println("TestComponent.testState: ${component.testState}")
        }

    }
}