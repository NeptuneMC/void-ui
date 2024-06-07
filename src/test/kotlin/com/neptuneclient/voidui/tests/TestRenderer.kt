package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.event.events.MouseMovementEvent
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.framework.Offset
import com.neptuneclient.voidui.framework.Size
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.theme.TextStyle
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.utils.ImageBuffer
import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NVGPaint
import org.lwjgl.nanovg.NanoVG
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryStack
import java.awt.Color
import java.nio.ByteBuffer
import java.nio.FloatBuffer
import java.nio.file.Path
import kotlin.math.max

class TestRenderer : Renderer {

    companion object {
        private const val NULL = 0L
    }

    private var vg: Long
    var window: Long

    private var framebufferWidth: Int = 0
    private var framebufferHeight: Int = 0

    private var contentScaleX: Float = 0f
    private var contentScaleY: Float = 0f

    init {
        if (!GLFW.glfwInit()) {
            throw IllegalStateException("Failed to initialize GLFW!")
        }

        GLFW.glfwWindowHint(GLFW.GLFW_VISIBLE, GLFW.GLFW_FALSE)
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MAJOR, 3)
        GLFW.glfwWindowHint(GLFW.GLFW_CONTEXT_VERSION_MINOR, 3)
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_PROFILE, GLFW.GLFW_OPENGL_CORE_PROFILE)
        GLFW.glfwWindowHint(GLFW.GLFW_OPENGL_FORWARD_COMPAT, GLFW.GLFW_TRUE)

        window = GLFW.glfwCreateWindow(1000, 600, "VoidUI Test", NULL, NULL)
        if (window == NULL) {
            GLFW.glfwTerminate()
            throw IllegalStateException("Failed to create window!")
        }

        GLFW.glfwSetFramebufferSizeCallback(window) { _, width, height ->
            framebufferWidth = width
            framebufferHeight = height
        }

        GLFW.glfwSetWindowContentScaleCallback(window) { _, x, y ->
            contentScaleX = x
            contentScaleY = y
        }

        GLFW.glfwSetMouseButtonCallback(window) { _, button, action, _ ->
            val (x, y) = mousePosition()
            mouseEvent(button, action, x, y)
        }

        GLFW.glfwSetCursorPosCallback(window) { _, x, y ->
            MouseMovementEvent(x.toFloat(), y.toFloat()).call(voidUI)
        }

        MemoryStack.stackPush().use { stack ->
            val fWidth = stack.mallocInt(1)
            val fHeight = stack.mallocInt(1)
            val cScaleX = stack.mallocFloat(1)
            val cScaleY = stack.mallocFloat(1)

            GLFW.glfwGetFramebufferSize(window, fWidth, fHeight)
            framebufferWidth = fWidth.get(0)
            framebufferHeight = fHeight.get(0)
            GLFW.glfwGetWindowContentScale(window, cScaleX, cScaleY)
            contentScaleX = cScaleX.get(0)
            contentScaleY = cScaleY.get(0)
        }

        GLFW.glfwMakeContextCurrent(window)
        GL.createCapabilities()
        GLFW.glfwSwapInterval(1)

        vg = NanoVGGL3.nvgCreate(NanoVGGL3.NVG_ANTIALIAS)
        if (vg == NULL) {
            throw IllegalStateException("Failed to create NanoVG context!")
        }

        GLFW.glfwShowWindow(window)
    }

    override fun destroy() {
        NanoVGGL3.nvgDelete(vg)
        GLFW.glfwDestroyWindow(window)
        GLFW.glfwTerminate()
    }

    override fun windowWidth(): Int {
        val width = IntArray(1)
        GLFW.glfwGetWindowSize(window, width, null)
        return width[0]
    }

    override fun windowHeight(): Int {
        val height = IntArray(1)
        GLFW.glfwGetWindowSize(window, null, height)
        return height[0]
    }

    override fun beginFrame() {
        val width = framebufferWidth / contentScaleX
        val height = framebufferHeight / contentScaleY

        GL11.glViewport(0, 0, framebufferWidth, framebufferHeight)
        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT or GL11.GL_DEPTH_BUFFER_BIT or GL11.GL_STENCIL_BUFFER_BIT)
        NanoVG.nvgBeginFrame(vg, width, height, max(contentScaleX, contentScaleY))
    }

    override fun endFrame() {
        NanoVG.nvgEndFrame(vg)
        GLFW.glfwSwapBuffers(window)
        GLFW.glfwPollEvents()
    }

    override fun mousePosition(): Offset {
        val x = DoubleArray(1)
        val y = DoubleArray(1)
        GLFW.glfwGetCursorPos(window, x, y)
        return Offset(x[0].toFloat(), y[0].toFloat())
    }

    override fun registerFont(name: String, data: ByteBuffer) {
        NanoVG.nvgCreateFontMem(vg, name, data, false)
    }

    override fun registerImage(path: Path, data: ByteBuffer): Int {
        return NanoVG.nvgCreateImageMem(vg, NanoVG.NVG_IMAGE_GENERATE_MIPMAPS, data)
    }

    override fun unregisterImage(image: ImageBuffer) {
        if (image.id != null)
            NanoVG.nvgDeleteImage(vg, image.id!!)
    }

    override fun scissor(x: Float, y: Float, width: Float, height: Float) {
        NanoVG.nvgScissor(vg, x, y, width, height)
    }

    override fun disableScissor() {
        NanoVG.nvgResetScissor(vg)
    }

    override fun line(x: Float, y: Float, x2: Float, y2: Float, thickness: Float, color: Color) {
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgMoveTo(vg, x, y)
            NanoVG.nvgLineTo(vg, x2, y2)
            NanoVG.nvgStrokeColor(vg, it)
            NanoVG.nvgStrokeWidth(vg, thickness)
            NanoVG.nvgStroke(vg)
            NanoVG.nvgClosePath(vg)
        }
    }

    private fun Color.use(block: (NVGColor) -> Unit) {
        val nvgColor = NVGColor.calloc().use {
            NanoVG.nvgRGBAf(red / 255f, green / 255f, blue / 255f, alpha / 255f, it)
            block(it)
        }
    }

    override fun rectangle(x: Float, y: Float, width: Float, height: Float, color: Color) {
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgRect(vg, x, y, width, height)
            NanoVG.nvgFillColor(vg, it)
            NanoVG.nvgFill(vg)
            NanoVG.nvgClosePath(vg)
        }
    }

    override fun rectangleFrame(x: Float, y: Float, width: Float, height: Float, thickness: Float, color: Color) {
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgRect(vg, x, y, width, height)
            NanoVG.nvgStrokeColor(vg, it)
            NanoVG.nvgStrokeWidth(vg, thickness)
            NanoVG.nvgStroke(vg)
            NanoVG.nvgClosePath(vg)
        }
    }

    override fun roundedRectangle(
        x: Float,
        y: Float,
        width: Float,
        height: Float,
        radius: CornerRadius,
        color: Color
    ) {
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgRoundedRectVarying(vg, x, y, width, height, radius.topLeft, radius.topRight, radius.bottomRight, radius.bottomLeft)
            NanoVG.nvgFillColor(vg, it)
            NanoVG.nvgFill(vg)
            NanoVG.nvgClosePath(vg)
        }
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
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgRoundedRectVarying(vg, x, y, width, height, radius.topLeft, radius.topRight, radius.bottomRight, radius.bottomLeft)
            NanoVG.nvgStrokeColor(vg, it)
            NanoVG.nvgStrokeWidth(vg, thickness)
            NanoVG.nvgStroke(vg)
            NanoVG.nvgClosePath(vg)
        }
    }

    override fun image(x: Float, y: Float, width: Float, height: Float, image: ImageBuffer, imageOffset: Offset) {
        if (image.id == null)
            throw IllegalStateException("Image was not registered properly!")

        val paint = NanoVG.nvgImagePattern(vg, x + imageOffset.x, y + imageOffset.y, image.size.width, image.size.height, 0f, image.id!!, 1.0F, NVGPaint.calloc())
        NanoVG.nvgBeginPath(vg)
        NanoVG.nvgRect(vg, x, y, width, height)
        NanoVG.nvgFillPaint(vg, paint)
        NanoVG.nvgFill(vg)
        NanoVG.nvgClosePath(vg)
        paint.free()
    }

    override fun roundedImage(x: Float, y: Float, width: Float, height: Float, radius: CornerRadius, image: ImageBuffer, imageOffset: Offset) {
        if (image.id == null)
            throw IllegalStateException("Image was not registered properly!")

        val paint = NanoVG.nvgImagePattern(vg, x + imageOffset.x, y + imageOffset.y, image.size.width, image.size.height, 0f, image.id!!, 1.0F, NVGPaint.calloc())
        NanoVG.nvgBeginPath(vg)
        NanoVG.nvgRoundedRectVarying(vg, x, y, width, height, radius.topLeft, radius.topRight, radius.bottomRight, radius.bottomLeft)
        NanoVG.nvgFillPaint(vg, paint)
        NanoVG.nvgFill(vg)
        NanoVG.nvgClosePath(vg)
        paint.free()
    }

    override fun text(x: Float, y: Float, text: String, font: Font, style: TextStyle) {
        val color = style.color
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgFillColor(vg, it)
            NanoVG.nvgFontFace(vg, font.name)
            NanoVG.nvgFontSize(vg, style.size.toFloat())
            NanoVG.nvgTextLetterSpacing(vg, style.letterSpacing)

            val bounds = BufferUtils.createFloatBuffer(4)
            NanoVG.nvgTextBounds(vg, x, y, text, bounds)
            NanoVG.nvgText(vg, x, y + (y - bounds[1]), text)
            NanoVG.nvgClosePath(vg)
        }
    }

    override fun getTextBounds(text: String, font: Font, style: TextStyle): Size {
        val buffer: FloatBuffer = BufferUtils.createFloatBuffer(4)

        NanoVG.nvgFontSize(vg, style.size.toFloat())
        NanoVG.nvgFontFace(vg, font.name)
        NanoVG.nvgTextLetterSpacing(vg, style.letterSpacing)
        NanoVG.nvgTextBounds(vg, 0f, 0f, text, buffer)
        return Size(buffer[2] - buffer[0], buffer[3] - buffer[1])
    }

    override fun compileShaderProgram(vertexSource: String, fragmentSource: String): Int {
        return 0
    }

    override fun deleteShaderProgram(program: Int) {
    }

    override fun useShaderProgram(program: Int) {
    }

    override fun setUniform(program: Int, name: String, value: Number) {
    }

    override fun setUniform(program: Int, name: String, value: Array<out Number>) {
    }

}