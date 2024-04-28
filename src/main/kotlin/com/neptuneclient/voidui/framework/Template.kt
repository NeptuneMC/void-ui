package com.neptuneclient.voidui.framework

/**
 * Define a template for all screens in the framework. The widget tree of the screen is represented as
 * the parameter in the function.
 *
 * If [com.neptuneclient.voidui.Settings.centeredScreen] is enabled the template is centered, not the widgets inside the template.
 */
fun interface Template {

    /**
     * Builds the template.
     *
     * @param slot The widgets of the screen, which will be built inside the template.
     *
     * @return The widget tree of the template.
     */
    fun build(slot: Widget): Widget

}