package co.mtarget.kohmon.string

import java.net.MalformedURLException
import java.net.URL

const val ALPHANUMERIC_PATTERN = "[a-zA-Z0-9]"
const val NUMERIC_PATTERN = "[0-9]"
const val ALPHABETH_PATTERN = "[a-zA-Z]"
const val HEX_STRING = "[0-9A-Fa-f]"
const val NUMBER_PATTERN = "-?\\d+(\\.\\d+)?"

fun String.toMatchRegex() = "^$this+\$".toRegex()

fun String.toContainsRegex() = ".*$this.*".toRegex()

fun String.toRegexIgnoreCase() = this.toRegex(RegexOption.IGNORE_CASE)

val String.isContainLetters get() = matches(ALPHABETH_PATTERN.toContainsRegex())

val String.isContainNumbers get() = matches(NUMERIC_PATTERN.toContainsRegex())

/**
 * Does not allow whitespace or symbols
 * Allows empty string
 */
val String.isAlphanumeric get() = matches(ALPHANUMERIC_PATTERN.toMatchRegex())

/**
 * Does not allow whitespace or symbols or empty string
 */
val String.isAlphabetic get() = matches(ALPHABETH_PATTERN.toMatchRegex())

/**
 * Does not allow whitespace or symbols or empty string
 */
val String.isNumericOnly get() = matches(NUMERIC_PATTERN.toMatchRegex())

val String.isNumber get() = matches(NUMBER_PATTERN.toMatchRegex())

/**
 * Only matches URLs starting with a scheme (e.g. http://, file://, ftp://, etc)
 */
val String.isUrl
    get() = try {
        URL(this)
        true
    } catch (e: MalformedURLException) {
        false
    }

val String?.isHexString get() = this?.matches(HEX_STRING.toMatchRegex()) ?: false