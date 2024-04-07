package com.neptuneclient.voidui.tests

import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.utils.Font
import com.neptuneclient.voidui.widgets.objects.EdgeInsets
import com.neptuneclient.voidui.widgets.objects.Size
import org.lwjgl.BufferUtils
import org.lwjgl.glfw.GLFW
import org.lwjgl.nanovg.NVGColor
import org.lwjgl.nanovg.NanoVG
import org.lwjgl.nanovg.NanoVGGL3
import org.lwjgl.opengl.GL
import org.lwjgl.opengl.GL11
import org.lwjgl.system.MemoryStack
import java.awt.Color
import java.nio.FloatBuffer
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

    fun destroy() {
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

    override fun registerFont(font: Font) {
        NanoVG.nvgCreateFontMem(vg, font.identifier, font.data, false)
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
        r0: Float,
        r1: Float,
        r2: Float,
        r3: Float,
        color: Color
    ) {
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgRoundedRectVarying(vg, x, y, width, height, r0, r1, r2, r3)
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
        r0: Float,
        r1: Float,
        r2: Float,
        r3: Float,
        thickness: Float,
        color: Color
    ) {
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgRoundedRectVarying(vg, x, y, width, height, r0, r1, r2, r3)
            NanoVG.nvgStrokeColor(vg, it)
            NanoVG.nvgStrokeWidth(vg, thickness)
            NanoVG.nvgStroke(vg)
            NanoVG.nvgClosePath(vg)
        }
    }

    override fun text(x: Float, y: Float, text: String, font: Font, color: Color) {
        color.use {
            NanoVG.nvgRGBAf(color.red / 255f, color.green / 255f, color.blue / 255f, color.alpha / 255f, it)
            NanoVG.nvgBeginPath(vg)
            NanoVG.nvgFillColor(vg, it)
            NanoVG.nvgFontFace(vg, font.identifier)
            NanoVG.nvgFontSize(vg, font.size.toFloat())
            NanoVG.nvgTextLetterSpacing(vg, font.letterSpacing.toFloat())

            val bounds = BufferUtils.createFloatBuffer(4)
            NanoVG.nvgTextBounds(vg, x, y, text, bounds)
            NanoVG.nvgText(vg, x, y + (y - bounds[1]), text)
            NanoVG.nvgClosePath(vg)
        }
    }

    override fun getTextBounds(text: String, font: Font): Size {
        val buffer: FloatBuffer = BufferUtils.createFloatBuffer(4)

        NanoVG.nvgFontSize(vg, font.size.toFloat())
        NanoVG.nvgFontFace(vg, font.identifier)
        NanoVG.nvgTextLetterSpacing(vg, font.letterSpacing.toFloat())
        NanoVG.nvgTextBounds(vg, 0f, 0f, text, buffer)
        return Size(buffer[2] - buffer[0], buffer[3] - buffer[1])
    }
}