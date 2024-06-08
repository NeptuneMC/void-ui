package com.neptuneclient.voidui.theme

import com.neptuneclient.voidui.event.events.MouseMovementEvent
import com.neptuneclient.voidui.framework.Screen
import com.neptuneclient.voidui.framework.Widget

abstract class StyledWidget<T : StyleSheet> : Widget() {

    private lateinit var styles: Styles<T>

    private var stylesheetState: T? by state(null)

    protected val stylesheet: T
        get() = stylesheetState!!

    override fun init(screen: Screen, parent: Widget) {
        try {
            this.voidUI = screen.voidUI
            this.screen = screen
            styles = voidUI.theme.getStyles(this::class)
            checkStylesheetUpdate()

            root = build()
            root!!.init(screen, this)
        } catch (t: Throwable) {
            t.printStackTrace()
        }

        registerEventAction(MouseMovementEvent::class) {
            //println("${hovered()} && ${stylesheetState == styles.hovered}")
            checkStylesheetUpdate()
        }
    }

    private fun checkStylesheetUpdate() {
        if (stylesheetState != styles.hovered && hovered()) {
            //println("hovered")
            stylesheetState = styles.hovered
        } else if (!hovered() && stylesheetState != styles.regular) {
            //println("regular")
            stylesheetState = styles.regular
        }
    }

}