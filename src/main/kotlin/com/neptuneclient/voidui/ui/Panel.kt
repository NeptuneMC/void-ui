package com.neptuneclient.voidui.ui

import com.neptuneclient.voidui.framework.Widget
import com.neptuneclient.voidui.objects.EdgeInsets
import com.neptuneclient.voidui.theme.StyledWidget
import com.neptuneclient.voidui.theme.stylesheets.PanelStyleSheet
import com.neptuneclient.voidui.widgets.Container

/**
 * TODO
 *
 * @param child The child widget inside the panel.
 * @param margin The margin around the panel.
 * @param padding The padding inside the panel.
 */
class Panel(
    private val child: Widget,
    private val margin: EdgeInsets = EdgeInsets.zero,
    private val padding: EdgeInsets = EdgeInsets.zero
) : StyledWidget<PanelStyleSheet>() {

    override fun build(): Widget {
        return Container(
            color = stylesheet.color,
            border = stylesheet.border,
            cornerRadius = stylesheet.cornerRadius,
            margin = margin,
            padding = padding,
            child = child
        )
    }

}