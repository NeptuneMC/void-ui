package com.neptuneclient.voidui.shaders

/**
 * A class which describes a uniform variable in a shader.
 *
 * A uniform object should only be created using the dsl functions like [bool] or [vec2].
 *
 * @param values The values which are supplied into the shader.
 */
class Uniform internal constructor(vararg var values: Number)

fun bool(value: Boolean) = Uniform(if (value) 1 else 0)
fun int(value: Int) = Uniform(value)
fun float(value: Float) = Uniform(value)
fun double(value: Double) = Uniform(value)

fun vec2(value0: Float, value1: Float) = Uniform(value0, value1)
fun vec3(value0: Float, value1: Float, value2: Float) = Uniform(value0, value1, value2)
fun vec4(value0: Float, value1: Float, value2: Float, value3: Float) = Uniform(value0, value1, value2, value3)

// also at matrices