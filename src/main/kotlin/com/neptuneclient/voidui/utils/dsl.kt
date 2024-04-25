package com.neptuneclient.voidui.utils

import java.nio.file.Path

/**
 * Creates a path object from the given string.
 */
fun path(path: String) = Path.of(path)

/**
 * Creates an image from the given string path.
 */
fun image(path: String) = ImageBuffer(path(path))