package com.neptuneclient.voidui.event

import java.lang.reflect.InvocationTargetException


/**
 * @author refactoring
 * @date 01-09-2023
 */
class EventManager {
    var targetClasses: MutableMap<Class<*>, Any> = HashMap()
    fun register(`object`: Any) {
        if (targetClasses.containsKey(`object`.javaClass)) return
        targetClasses[`object`.javaClass] = `object`
    }

    fun unregister(`object`: Any) {
        targetClasses.remove(`object`.javaClass)
    }

    fun clearTargets() {
        targetClasses.clear()
    }

    fun fire(event: Event) {
        targetClasses.forEach { (clazz: Class<*>, `object`: Any?) ->
            for (method in clazz.getDeclaredMethods()) {
                if (method.isAnnotationPresent(Subscribe::class.java) && method.getAnnotation(
                        Subscribe::class.java
                    ).target == event.javaClass
                ) {
                    try {
                        method.invoke(`object`, event)
                    } catch (e: IllegalAccessException) {
                        throw RuntimeException(e)
                    } catch (e: InvocationTargetException) {
                        throw RuntimeException(e)
                    }
                }
            }
        }
    }

    companion object {
        val instance = EventManager()
    }
}