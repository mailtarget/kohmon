package co.mtarget.kohmon.string

import java.util.HashMap

infix fun String?.ifNotEmpty(str: String?) = if (str.isNullOrEmpty()) this else str

infix fun String?.ifEmpty(str: String): String = if (this.isNullOrEmpty()) str else this

fun String.capitalizeFully(): String = split(" ").map { it.toLowerCase().capitalize() }.joinToString(" ")

fun String.countChar(c: Char) = this.count { it == c }

/**
 * Splits by spaces, newlines, and tabs only
 */
val String.camelCased: String
    get() {
        val split = toLowerCase().split(' ', '\n', '\t').toMutableList()
        if (split.size > 1) {
            for (i in 1 until split.size) {
                if (split[i].length > 1) {
                    val charArray = split[i].toCharArray()
                    charArray[0] = charArray[0].toUpperCase()
                    split[i] = String(charArray)
                }
            }
        }
        return split.joinToString("")
    }

/**
 * If there is more than one most common character, this returns the character that occurred first in the String
 */
val String.mostCommonCharacter: Char?
    get() {
        if (length == 0) return null
        val map = HashMap<Char, Int>()
        for (char in toCharArray()) map.put(char, (map[char] ?: 0) + 1)
        var maxEntry = map.entries.elementAt(0)
        for (entry in map) maxEntry = if (entry.value > maxEntry.value) entry else maxEntry
        return maxEntry.key
    }

fun String.splitComma() = split(",").map { it.trim() }