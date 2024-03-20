package com.neptuneclient.voidui.themes.objects

import java.awt.Color

/**
 * Holds values which represent a drop shaodw.
 *
 * @param x The x offset from the element.
 * @param y The y offset from the element.
 * @param blur The blur of the shadow.
 * @param spread The spread of the blur in all four directions.
 * @param color The color of the blur.
 */
data class DropShadow(val x: Int, val y: Int, val blur: Float, val spread: Int, val color: Color)