package com.neptuneclient.voidui.ui

sealed class Component {

    abstract fun build(): Component

}