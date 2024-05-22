package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.event.MouseClickedEvent
import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.theme.TextStyle
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.utils.ImageBuffer
import org.lwjgl.glfw.GLFW
import java.awt.Color
import java.nio.ByteBuffer
import java.nio.file.Path

class MockRenderer : Renderer {

    private var eventCounter = 0

    override fun windowWidth(): Int {
        println("windowWidth")
        return 0
    }

    override fun windowHeight(): Int {
        println("windowHeight")
        return 0
    }

    override fun beginFrame() {
        eventCounter++
        if (eventCounter >= 1000) {
            mouseEvent(0, GLFW.GLFW_PRESS, 0f, 0f)
            eventCounter = 0
        }

        println("beginFrame")
    }

    override fun endFrame() {
        println("endFrame")
    }

    override fun destroy() {
        println("destroy")
    }

    override fun mousePosition(): Offset {
        println("mousePosition")
        return Offset.zero
    }

    override fun registerFont(name: String, data: ByteBuffer) {
        println("registerFont")
    }

    override fun registerImage(path: Path, data: ByteBuffer): Int {
        println("registerImage")
        return 0
    }

    override fun unregisterImage(image: ImageBuffer) {
        println("unregisterImage")
    }

    override fun scissor(x: Float, y: Float, width: Float, height: Float) {
        println("scissor")
    }

    override fun disableScissor() {
        println("disableScissor")
    }

    override fun line(x: Float, y: Float, x2: Float, y2: Float, thickness: Float, color: Color) {
        println("line")
    }

    override fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color) {
        println("rectangle")
    }

    override fun rectangleFrame(x: Float, y: Float, width: Float, height: Float, thickness: Float, color: Color) {
        println("rectangleFrame")
    }

    override fun roundedRectangle(x: Float, y: Float, width: Float, height: Float, radius: CornerRadius, color: Color) {
        println("roundedRectangle")
    }

    override fun roundedRectangleFrame(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: CornerRadius,
        thickness: Float,
        color: Color
    ) {
        println("roundedRectangleFrame")
    }

    override fun image(x: Float, y: Float, width: Float, height: Float, image: ImageBuffer, imageOffset: Offset) {
        println("image")
    }

    override fun roundedImage(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: CornerRadius,
        image: ImageBuffer,
        imageOffset: Offset
    ) {
        println("roundedImage")
    }

    override fun text(x: Float, y: Float, text: String, font: Font, style: TextStyle) {
        println("text")
    }

    override fun getTextBounds(text: String, font: Font, style: TextStyle): Size {
        println("getTextBounds")
        return Size.zero
    }

    override fun compileShaderProgram(vertexSource: String, fragmentSource: String): Int {
        println("compileShaderProgram")
        return 0
    }

    override fun deleteShaderProgram(program: Int) {
        println("deleteShaderProgram")
    }

    override fun useShaderProgram(program: Int) {
        println("useShaderProgram")
    }

    override fun setUniform(program: Int, name: String, value: Number) {
        println("setUniform")
    }

    override fun setUniform(program: Int, name: String, value: Array<out Number>) {
        println("setUniform")
    }

}