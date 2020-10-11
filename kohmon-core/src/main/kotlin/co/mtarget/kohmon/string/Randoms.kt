package co.mtarget.kohmon.string

import java.security.SecureRandom
import kotlin.experimental.and

val ALPHANUMERIC_CHAR = ('a'..'z') + ('A'..'Z') + ('0'..'9')
val ALPHANUMERIC_LOWERCASE_CHAR = ('a'..'z') + ('0'..'9')

open class Randoms(private val chars: List<Char>, private val length: Int = 16) {
    private val random = SecureRandom()
    fun next(length: Int = this.length): String {
        val bytes = ByteArray(length)
        random.nextBytes(bytes)
        return (bytes.indices)
                .map { i ->
                    chars[(bytes[i] and 0xFF.toByte() and (chars.size-1).toByte()).toInt()]
                }.joinToString("")
    }
}

object RandomString: Randoms(ALPHANUMERIC_CHAR)

object RandomLowercase: Randoms(ALPHANUMERIC_LOWERCASE_CHAR)