package co.mtarget.kohmon.string

import java.util.*

/**
 * Using UTF-8 encoding
 */
val String.base64Decoded
    get() = try {
        String(Base64.getDecoder().decode(toByteArray()))
    } catch (e: IllegalArgumentException) {
        null
    }

/**
 * Using UTF-8 encoding
 */
val String.base64Encoded: String get() = Base64.getEncoder().encodeToString(toByteArray())
