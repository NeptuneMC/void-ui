package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.event.MouseClickedEvent
import com.neptuneclient.voidui.event.MouseReleasedEvent
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.themes.StyleSheet
import com.neptuneclient.voidui.themes.Styles
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet
import com.neptuneclient.voidui.units.LengthUnit
import com.neptuneclient.voidui.widgets.objects.BoxConstraints
import com.neptuneclient.voidui.widgets.objects.Offset
import com.neptuneclient.voidui.widgets.objects.Size

/**
 * A custom type of widget which can not be created by the user. Elements can have a [StyleSheet] attached to them, which
 * makes them able to change through themes. Elements can also be rendered to the screen.
 *
 * When initialized, elements will be pushed onto the element stack. The element stack is rendered each frame. This is to prevent the tree from being iterated for every
 * single frame.
 *
 * @param S The class which holds the style sheet for that element, e.g. [PanelStyleSheet].
 */
sealed class Element<S : StyleSheet>(width: LengthUnit? = null, height: LengthUnit? = null) : Widget(width, height) {

    private lateinit var styles: Styles<S>

    /**
     * The style sheet for this element.
     */
    protected val styleSheet: S
        get() = if (active) {
            styles.active
        } else {
            if (hovered())
                styles.hovered
            else
                styles.normal
        }

    /**
     * Sizes the component and pushes itself to the screen's element stack.
     *
     * @param screen The screen in which this widget lives.
     * @param parent The parent widget. If this is the first widget in the tree, the parent widget is null.
     */
    override fun init(screen: Screen, parent: Widget?) {
        this.screen = screen
        this.parent = parent
        this.styles = screen.void.theme.getStyles(this::class)
        screen.elementStack.push(this)

        eventHandler.register(MouseClickedEvent::class) {
            if (hovered())
                active = true
        }
        eventHandler.register(MouseReleasedEvent::class) {
            active = false
            if (hovered())
                widgetClicked()
        }
    }

    override fun layout(parentOffset: Offset, constraints: BoxConstraints) {
        val width = if (width != null)
            this.width.getPixels(screen, constraints.maxWidth)
        else
            constraints.minWidth

        val height = if (height != null)
            this.height.getPixels(screen, constraints.maxHeight)
        else
            constraints.minWidth

        offset = parentOffset
        size = constraints.constrain(Size(width, height))
    }
    
    /**
     * The build method is never called on elements, as long as it isn't done manually when overriding the [init] method.
     * That's why the return value doesn't actually matter here.
     */
    override fun build(): Widget {
        return this
    }
    
    /**
     * Called on the element stack and renders the element after it's been initialized and sized.
     *
     * @param renderer The renderer defined in [VoidUI.renderer].
     */
    abstract fun render(renderer: Renderer)

}