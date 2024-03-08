package com.neptuneclient.voidui.ui

import kotlin.properties.Delegates
import kotlin.reflect.KMutableProperty
import kotlin.reflect.full.declaredMemberProperties
import kotlin.reflect.full.findAnnotation
import kotlin.reflect.jvm.javaField

/**
 * @see Component
 */
abstract class ReactiveComponent : Component() {

    init {
        for (member in this::class.declaredMemberProperties) {
            println("Member $member")
            println("Member annotations size: ${member.annotations.size}")
            if (member.findAnnotation<State>() == null) continue
            println("Member $member is a state")
            val memberObserver = Delegates.observable(member.getter.call(this)) { _, _, new ->
                println("Member $member changed to $new")
                this.build()
                // TODO do proper rebuilding in the component tree
            }

            if (member is KMutableProperty<*>) {
                member.setter.call(this, memberObserver)
            }
        }
    }

}