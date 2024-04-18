package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.framework.*
import com.neptuneclient.voidui.objects.CornerRadius
import com.neptuneclient.voidui.rendering.Renderer

class Image(
    private val src: com.neptuneclient.voidui.utils.Image,
    private val imageSize: Size? = null,
    private val cornerRadius: CornerRadius? = null
) : LeafWidget() {

    private lateinit var radius: CornerRadius

    override fun init(screen: Screen, parent: Widget) {
        super.init(screen, parent)

        radius = cornerRadius ?: theme.defaultStyles.image.cornerRadius
        src.register(screen.voidUI.renderer)
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        offset = parentOffset
        size = constraints.constrain(imageSize ?: Size(constraints.maxWidth, constraints.maxHeight))
    }

    override fun render(renderer: Renderer) {
        if (!radius.isEmpty())
            renderer.roundedImage(offset.x, offset.y, size.width, size.height, radius, src)
        else
            renderer.image(offset.x, offset.y, size.width, size.height, src)
    }

    override fun remove() {
        src.delete(screen.voidUI.renderer)
    }
}