package com.neptuneclient.voidui.event.events

import com.neptuneclient.voidui.event.Event

/**
 * Fired once a mouse button is pressed down.
 *
 * **This event needs to be called when implementing the library.**
 *
 * @param button The mouse button which was pressed
 * @param x The x position of the mouse.
 * @param y The y position of the mouse.
 */
class MouseClickedEvent(val button: Int, val x: Float, val y: Float) : Event()

/**
 * Fired once a mouse button is released.
 *
 * **This event needs to be called when implementing the library.**
 *
 * @param button The mouse button which was released.
 * @param x The x position of the mouse.
 * @param y The y position of the mouse.
 */
class MouseReleasedEvent(val button: Int, val x: Float, val y: Float) : Event()