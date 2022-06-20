package co.mtarget.kohmon.string

import java.util.HashMap

fun String.toBoolean(): Boolean {
    return this != ""
            && (this.equals("TRUE", ignoreCase = true)
            || this.equals("Y", ignoreCase = true)
            || this.equals("YES", ignoreCase = true))
}

/**
 * split string to list by comma
 */
fun String.splitComma(): List<String> = split(",").map { it.trim() }

/**
 * Capitalize every word in string
 */
fun String.capitalizeFully(): String = split(" ").joinToString(" ") { it.lowercase().capitalize() }

/**
 * Count characters in string
 */
fun String.countChar(c: Char) = this.count { it == c }

/**
 * Splits by spaces, newlines, and tabs only
 */
fun String.camelCased(): String {
    val split = lowercase().split(' ', '\n', '\t').toMutableList()
    if (split.size > 1) {
        for (i in 1 until split.size) {
            if (split[i].length > 1) {
                val charArray = split[i].toCharArray()
                charArray[0] = charArray[0].uppercaseChar()
                split[i] = String(charArray)
            }
        }
    }
    return split.joinToString("")
}

/**
 * If there is more than one most common character,
 * this returns the character that occurred first in the String
 */
fun String.getMostChar(): Char? {
    if (length == 0) return null
    val map = HashMap<Char, Int>()
    for (char in toCharArray()) map.put(char, (map[char] ?: 0) + 1)
    var maxEntry = map.entries.elementAt(0)
    for (entry in map) maxEntry = if (entry.value > maxEntry.value) entry else maxEntry
    return maxEntry.key
}
