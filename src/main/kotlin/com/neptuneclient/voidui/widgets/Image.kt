package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.*
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.rendering.RenderObject
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.utils.ImageBuffer

/**
 * A widget which renders an image to the screen.
 *
 * @param src The source of the image.
 * @param imageSize A custom size for the image, if this is not set, the image will cover the entire parent widget.
 * @param cornerRadius A custom corner radius for the image, if this is not set, the default value from the theme will be used.
 * @param fit Sizes the image in relation to the parents constraints if [imageSize] is not set.
 */
class Image(
    private val src: ImageBuffer,
    private val imageSize: Size? = null,
    private val cornerRadius: CornerRadius? = null,
    private val fit: ImageFit = ImageFit.KEEP
) : LeafWidget() {

    private lateinit var radius: CornerRadius

    /**
     * The position of the image relative to the position of the widget.
     */
    private var imageOffset = Offset.zero

    override fun init(screen: Screen, parent: Widget) {
        super.init(screen, parent)

        radius = cornerRadius ?: theme.defaultStyles.image.cornerRadius
        src.register(screen.voidUI.renderer)
    }

    override fun layout(constraints: BoxConstraints) {
        if (imageSize != null) {
            size = constraints.constrain(imageSize)
            return
        }

        if (fit == ImageFit.KEEP) {
            size = constraints.constrain(src.size)
            return
        }
        size = Size(constraints.maxWidth, constraints.maxHeight)

        src.size = when (fit) {
            ImageFit.STRETCH -> Size(constraints.maxWidth, constraints.maxHeight)
            ImageFit.COVER -> {
                if (src.size.width > src.size.height) {
                    val ratio = src.size.height / constraints.maxHeight
                    Size(src.size.width / ratio, src.size.height / ratio)
                } else {
                    val ratio = src.size.width / constraints.maxWidth
                    Size(src.size.width / ratio, src.size.height / ratio)
                }
            }
            // TODO fix the issue with this some time
            ImageFit.CONTAIN -> {
                if (src.size.width > src.size.height) {
                    val ratio = src.size.width / constraints.maxWidth
                    Size(src.size.width / ratio, src.size.height / ratio)
                } else {
                    val ratio = src.size.height / constraints.maxHeight
                    Size(src.size.width / ratio, src.size.height / ratio)
                }
            }
            ImageFit.KEEP -> src.size
        }
    }

    override fun postLayoutInit(parentOffset: Offset, parent: Widget) {
        if (fit == ImageFit.COVER) {
            imageOffset = if (src.size.width > src.size.height) {
                val overflow = src.size.width - size.width
                Offset(-overflow / 2f, 0f)
            } else {
                val overflow = src.size.height - size.height
                Offset(0f, -overflow / 2f)
            }
        }
        super.postLayoutInit(parentOffset, parent)
    }

    override fun createRenderObject(): RenderObject? {
        return ImageRenderObject(offset, size, src, radius, imageOffset)
    }

    override fun remove() {
        super.remove()
        src.delete(screen.voidUI.renderer)
    }
}

private class ImageRenderObject(
    offset: Offset,
    size: Size,
    private val image: ImageBuffer,
    private val radius: CornerRadius,
    private val imageOffset: Offset
) : RenderObject(offset, size) {

    override fun render(renderer: Renderer) {
        if (!radius.isEmpty())
            renderer.roundedImage(offset, size, radius, image, imageOffset)
        else
            renderer.image(offset, size, image, imageOffset)
    }

}

/**
 * Describes how the image should be sized in relation to the parent's constraints.
 */
enum class ImageFit {
    /**
     * The image will keep its original size in pixels.
     */
    KEEP,

    /**
     * The image will be stretched to fit within the constraints perfectly.
     */
    STRETCH,

    /**
     * The image will keep its ratio but will be scaled to cover the entire constraints.
     */
    COVER,

    /**
     * The image will keep its ratio but will be sized to not overflow the constraints.
     */
    CONTAIN
}