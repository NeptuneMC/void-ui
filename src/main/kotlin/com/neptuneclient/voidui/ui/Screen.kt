package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.VoidUI
import kotlin.math.round

abstract class Screen(private val void: VoidUI) {

    // TODO add a resize event or something
    var width = void.renderer.windowWidth()
    var height = void.renderer.windowHeight()

    private lateinit var body: Drawable

    abstract fun build(): Drawable

    fun assemble() {
        body = build()
        body.void = void
    }

    fun render() {
        void.renderer.beginFrame()
        body.render()
        void.renderer.endFrame()
    }

    /**
     * A dsl feature which adds the view-width unit from HTML to screens.
     */
    inline val Number.vw
        get() = round(width / 100 * this.toFloat()).toInt()

    /**
     * A dsl feature which adds the view-height unit from HTML to screens.
     */
    inline val Number.vh
        get() = round(height / 100 * this.toFloat()).toInt()

}