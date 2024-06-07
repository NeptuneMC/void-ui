package com.neptuneclient.voidui.theme

import com.neptuneclient.voidui.event.events.MouseMovementEvent
import com.neptuneclient.voidui.framework.Screen
import com.neptuneclient.voidui.framework.Widget

abstract class StyledWidget<T : StyleSheet> : Widget() {

    private lateinit var styles: Styles<T>

    protected lateinit var stylesheet: T

    override fun init(screen: Screen, parent: Widget) {
        this.voidUI = screen.voidUI
        this.screen = screen
        styles = voidUI.theme.getStyles(this::class)
        stylesheet = styles.regular

        root = build()
        root!!.init(screen, this)

        registerEventAction(MouseMovementEvent::class) {
            if (stylesheet != styles.hovered && hovered())
                stylesheet = styles.hovered
            else if (!hovered())
                stylesheet = styles.regular
        }
    }

}