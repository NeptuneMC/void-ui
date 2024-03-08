package com.neptuneclient.voidui.ui

/**
 * Used in implementations of [ReactiveComponent] to declare fields as state.
 *
 * For more info check out [ReactiveComponent].
 */
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD, AnnotationTarget.PROPERTY)
annotation class State()