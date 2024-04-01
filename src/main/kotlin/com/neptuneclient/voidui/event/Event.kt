package com.neptuneclient.voidui.event

import com.neptuneclient.voidui.VoidUI

/**
 * The base class for events in VoidUI.
 */
abstract class Event {

    /**
     * Calls the event.
     *
     * @param void The instance of VoidUI in which the event will be called.
     */
    fun call(void: VoidUI) = void.eventHandler.eventCalled(this)

}