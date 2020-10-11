package co.mtarget.kohmon

/**
 * Try executing operation, if there is exceptions, return null
 */
inline fun <T> tryOrNull(block: () -> T): T? =
        try { block() } catch (e: Throwable) { null }

private class RetryException : Exception()

/**
 * Retry error operation null until x time
 */
suspend fun <T> retryError(n: Int, block: suspend (Int) -> T): T {
    for (i in 1..n) {
        try {
            return block(i)
        } catch (e: Exception) { }
    }
    // last time just invoke regardless result
    return block(0)
}

/**
 * Retry operation if value return null until x time
 */
suspend fun <T> retryNull(until: Int, block: suspend (Int) -> T): T {
    for (i in 1..until) {
        try {
            return block(i) ?: throw RetryException()
        } catch (e: RetryException) { }
    }
    // last time just invoke regardless result
    return block(0)
}