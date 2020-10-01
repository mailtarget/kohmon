package co.mtarget.kohmon

inline fun <T> justTry(block: () -> T) = try {
    block()
} catch (e: Throwable) {
    null
}

private class RetryException : Exception()

/**
 * @param n Try n times
 */
suspend fun <T> retryUntilNotNull(n: Int, block: suspend (Int) -> T): T {
    for (i in 1..n) { // try 5 times
        try {
            return block(i) ?: throw RetryException()
        } catch (e: RetryException) { /* retry */
        }
    }
    return block(0) // last time just invoke regardless result
}

/**
 * @param n Try n times
 */
suspend fun <T> retryUntilNoException(n: Int, block: suspend (Int) -> T): T {
    for (i in 1..n) { // try 5 times
        try {
            return block(i)
        } catch (e: Exception) { /* retry */
        }
    }
    return block(0) // last time just invoke regardless result
}