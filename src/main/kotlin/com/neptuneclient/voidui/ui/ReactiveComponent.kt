package com.neptuneclient.voidui.ui

import kotlin.properties.Delegates
import kotlin.properties.ObservableProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KMutableProperty
import kotlin.reflect.KProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.full.getExtensionDelegate
import kotlin.reflect.jvm.javaField

/**
 * @see Component
 */
abstract class ReactiveComponent : Component() {

    /**
     * Used to initialize a stateful variable. Whenever a stateful variable changes, the component gets rebuild.
     *
     * @param initialValue The initial value of the variable
     *
     * @sample ``var testState by state(2)`` - Defines a stateful variable with an initial value of 2.
     */
    fun <T> state(initialValue: T) = Delegates.observable(initialValue) { _, _, new ->
        println("gndfjldsfs: $new")
        this.build()
        // TODO handle component rebuilding properly
    }

}