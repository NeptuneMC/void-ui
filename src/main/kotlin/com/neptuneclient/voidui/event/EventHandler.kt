package com.neptuneclient.voidui.event

import kotlin.reflect.KClass

/**
 * Handles all event calls of the library.
 */
class EventHandler {

    /**
     * A table of all actions which are registered.
     *
     * See [ActionRegistry] for more info.
     */
    private val actions = mutableListOf<ActionRegistry>()

    /**
     * Register a new action.
     *
     * @param action The action which will be invoked at the event call.
     * @param event The class of the event which will trigger the action.
     * @param key An optional key which will identify an action when it gets unregistered, keys don't need to be unique but
     * in case of a duplicate key all actions with this key will get unregistered.
     */
    fun <T : Event> register(event: KClass<T>, key: Any? = null, action: (T) -> Unit) {
        actions.add(ActionRegistry(action as (Event) -> Unit, event as KClass<Event>, key))
    }

    /**
     * Invokes all actions which are registered with the provided event.
     */
    fun eventCalled(event: Event) {
        for ((action, eventClass, _) in actions) {
            if (event::class != eventClass) continue
            action(event)
        }
    }

    /**
     * Unregisters all actions with the provided key.
     */
    fun unregister(key: Any) {
        val indexCache = mutableListOf<Int>()

        // fuck you ConcurrentModificationException
        for ((index, data) in actions.withIndex()) {
            if (key != data.key) continue
            indexCache.add(index)
        }

        for (index in indexCache)
            actions.removeAt(index)
    }

    /**
     * Unregisters all actions.
     */
    fun unregisterAll() {
        actions.clear()
    }

}

/**
 * Stores information about an event action.
 *
 * @param action The action which will be invoked at the event call.
 * @param event The class of the event which will trigger the action.
 * @param key An optional key which will identify an action when it gets unregistered, keys don't need to be unique but
 * in case of a duplicate key all actions with this key will get unregistered.
 */
private data class ActionRegistry(val action: (Event) -> Unit, val event: KClass<Event>, val key: Any?)