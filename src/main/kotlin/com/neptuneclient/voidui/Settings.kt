package com.neptuneclient.voidui

/**
 * Holds values which can change the behaviour of the framework.
 */
data class Settings(
    /**
     * Decides whether the widgets in a screen should be centered on the screen automatically.
     * When this option off the widgets will be placed in the top left corner of the screen.
     */
    val centeredScreen: Boolean = true,

    /**
     * When enabled it draws a white box around each widget on the screen.
     *
     * TODO implement this
     */
    val debugWidgetSize: Boolean = false
)