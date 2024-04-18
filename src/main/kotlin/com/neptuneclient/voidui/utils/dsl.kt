package com.neptuneclient.voidui.utils

import java.nio.file.Path

fun path(path: String) = Path.of(path)

fun image(path: String) = Image(path(path))