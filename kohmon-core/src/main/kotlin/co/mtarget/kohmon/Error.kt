package co.mtarget.kohmon

import java.lang.IllegalArgumentException

/**
 * get stackTrace from exception, usable in logging error
 */
fun Throwable.getStackTraceAsString(): String {
    return (if (!this.localizedMessage.isNullOrBlank()) this.localizedMessage.plus("\n") else "")
        .plus(this.stackTrace.joinToString("\n") { it.toString() })
}

/**
 * helper function in checking required condition
 */
fun illegal(message: String = "Illegal argument"): Nothing = throw IllegalArgumentException(message)


