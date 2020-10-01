@file:Suppress("RECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
package co.mtarget.kohmon

import java.io.InputStream
import kotlin.reflect.KClass

fun KClass<*>.getResource(name: String): String? = try {
    this::class.java.classLoader.getResource(name).readText()
} catch (t: Throwable) { null }

fun KClass<*>.getResourceAsStream(name: String): InputStream? = try {
    this::class.java.classLoader.getResourceAsStream(name)
} catch (t: Throwable) { null }

fun Any.getResource(name: String): String? = this::class.getResource(name)

fun Any.getResourceAsStream(name: String): InputStream? = this::class.getResourceAsStream(name)
