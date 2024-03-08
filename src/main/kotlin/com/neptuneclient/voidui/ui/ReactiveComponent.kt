package com.neptuneclient.voidui.ui

import kotlin.properties.Delegates

/**
 * @see Component
 */
abstract class ReactiveComponent : Component() {

    init {
        for (field in javaClass.declaredFields) {
            if (!field.isAnnotationPresent(State::class.java)) continue
            val fieldObserver = Delegates.observable(field.get(this)) { _, _, new ->
                this.build()
                // TODO do proper rebuilding in the component tree
            }
            field.set(this, fieldObserver)
        }
    }

}