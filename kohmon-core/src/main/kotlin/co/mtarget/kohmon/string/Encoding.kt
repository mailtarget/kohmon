package co.mtarget.kohmon.string

import co.mtarget.kohmon.tryOrNull
import java.util.*

/**
 * Using UTF-8 encoding
 */
fun String.base64Decoded() = tryOrNull { String(Base64.getDecoder().decode(toByteArray())) }

/**
 * Using UTF-8 encoding
 */
fun String.base64Encoded(): String = Base64.getEncoder().encodeToString(toByteArray())
