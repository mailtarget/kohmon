package co.mtarget.kohmon.string

import co.mtarget.kohmon.tryOrNull
import java.net.MalformedURLException
import java.net.URL

/**
 * check if string contains letter
 */
fun String.isContainLetters() = matches(ALPHABETH_PATTERN.toContainsRegex())

/**
 * check if string contains number
 */
fun String.isContainNumbers() = matches(NUMERIC_PATTERN.toContainsRegex())

/**
 * Does not allow whitespace or symbols
 * Allows empty string
 */
fun String.isAlphanumeric() = matches(ALPHANUMERIC_PATTERN.toMatchRegex())

/**
 * Does not allow whitespace or symbols or empty string
 */
fun String.isAlphabetic() = matches(ALPHABETH_PATTERN.toMatchRegex())

/**
 * Does not allow whitespace or symbols or empty string
 */
fun String.isNumericOnly() = matches(NUMERIC_PATTERN.toMatchRegex())

fun String.isNumber() = matches(NUMBER_PATTERN.toMatchRegex())

/**
 * Only matches URLs starting with a scheme (e.g. http://, file://, ftp://, etc)
 */
fun String.isUrl(): Boolean = tryOrNull { URL(this) } != null

/**
 * check if string match hexadecimal representation
 */
fun String?.isHexString() = this?.matches(HEX_STRING.toMatchRegex()) ?: false

/**
 * check if string match phone number
 */
fun String.isPhoneNumber() = matches(PHONE_PATTERN.toMatchRegex())

/**
 * check if string match ip address
 */
fun String.isIPAddress() = matches(IP_ADDRESS_PATTERN.toMatchRegex())

/**
 * check if string match web url
 */
fun String.isWebUrl() = if (matches(WEB_URL.toMatchRegex())) {
    true
} else matches(AUTOLINK_WEB_URL.toMatchRegex())

/**
 * check if string match email format
 */
fun String.isEmail() = if (matches(EMAIL_PATTERN.toMatchRegex())) {
    true
} else matches(AUTOLINK_EMAIL_ADDRESS.toMatchRegex())
