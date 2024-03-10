package com.neptuneclient.voidui.utils

import com.neptuneclient.voidui.VoidUI
import java.io.IOException
import java.nio.ByteBuffer
import java.nio.channels.Channels
import java.nio.file.Path

/**
 * Reads a given file into a [ByteBuffer] which can then be parsed by the renderer. This is
 * used to load fonts and images in NanoVG.
 *
 * @param path The path to the file to read.
 * @param bufferSize The default size of the buffer. If the buffer runs out of space, it will be resized.
 *
 * @return The byte buffer containing the file data.
 *
 * @throws IOException If an error occurs while reading the file.
 * @throws IllegalArgumentException If the [bufferSize] is a negative number.
 */
@Throws(IOException::class, IllegalArgumentException::class)
fun getBufferData(path: Path, bufferSize: UInt = 1024u): ByteBuffer {
    val stream = VoidUI::class.java.classLoader.getResourceAsStream(path.toString()) ?:
        throw IOException("Could not find the resource at the provided path!")
    val channel = Channels.newChannel(stream) ?:
        throw IOException("Could not open the resource at the provided path!")

    var buffer = ByteBuffer.allocateDirect(bufferSize.toInt()) ?:
        throw IllegalArgumentException("The buffer size must be a positive integer!")

    while (channel.read(buffer) != -1) {
        if (buffer.remaining() > 0) continue

        // if buffer is full
        val newBuffer = ByteBuffer.allocateDirect(buffer.capacity() * 3 / 2)
        buffer.flip()
        newBuffer.put(buffer)
        buffer = newBuffer
    }

    buffer.flip()
    return buffer.slice()
}