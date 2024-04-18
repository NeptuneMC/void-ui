package com.neptuneclient.voidui

import com.neptuneclient.voidui.event.EventHandler
import com.neptuneclient.voidui.rendering.Renderer
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * The main class of VoidUI. It is mostly used to contain settings and singleton instances of other classes
 * like the renderer in it. It has to be passed whenever creating a screen, however it does not handle any of
 * the screen displaying logic, since this is supposed to be handled through Minecraft.
 */
class VoidUI
/**
 * @param renderer The renderer used to draw all components in the screens. Void does not come with a renderer implementation
 * out of the box so the user has to create his own implementation.
 *
 * @param theme The theme used to style UI elements.
 */
constructor(val renderer: Renderer, ) {

    /**
     * The event handler which handles all events in the instance.
     */
    val eventHandler = EventHandler()

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger("VoidUI")
    }

    /**
     * Destroys the library and cleans everything up.
     */
    fun destroy() {
        renderer.destroy()
    }

}