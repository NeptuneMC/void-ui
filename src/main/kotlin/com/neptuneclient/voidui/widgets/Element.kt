package com.neptuneclient.voidui.widgets

import com.neptuneclient.voidui.VoidUI
import com.neptuneclient.voidui.rendering.Renderer
import com.neptuneclient.voidui.themes.StyleSheet
import com.neptuneclient.voidui.themes.Styles
import com.neptuneclient.voidui.themes.styles.PanelStyleSheet

/**
 * A custom type of widget which represents a leaf node in the tree. This means that nodes of type [Element] will never have children, with the exception of the [Group] element.
 * Elements can also not be created by the user of the library.
 *
 * When initialized, elements will be pushed onto the element stack. The element stack is rendered each frame. This is to prevent the tree from being iterated for every
 * single frame.
 *
 * @param S The class which holds the style sheet for that element, e.g. [PanelStyleSheet].
 */
sealed class Element<S : StyleSheet> : Widget() {

    /**
     * Holds the screen in which the element is rendered.
     */
    private lateinit var screen: Screen

    /**
     * The style sheet for this element.
     */
    protected lateinit var styles: S

    /**
     * Sizes the component and pushes itself to the screen's element stack.
     *
     * @param screen The screen in which this widget lives.
     * @param parent The parent widget. If this is the first widget in the tree, the parent widget is null.
     */
    override fun init(screen: Screen, parent: Widget?) {
        this.screen = screen
        this.styles = screen.void.theme.getStyleSheet(this::class, Styles.Type.NORMAL)

        val size = sizeSelf(screen, parent)
        x = size.x
        y = size.y
        width = size.width
        height = size.height
        
        screen.elementStack.push(this)
    }
    
    /**
     * The build method is never called on elements. Thats why the return value doesn't actually matter here.
     */
    final override fun build(): Widget {
        return this
    }
    
    /**
     * Called on the element stack and renders the element after its been initialized and sized.
     *
     * @param renderer The renderer defined in [VoidUI.renderer].
     */
    abstract fun render(renderer: Renderer)
    
}