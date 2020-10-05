package co.mtarget.kohmon.string

import java.net.MalformedURLException
import java.net.URL

/**
 * check if string contains letter
 */
val String.isContainLetters get() = matches(ALPHABETH_PATTERN.toContainsRegex())

/**
 * check if string contains number
 */
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

/**
 * check if string match hexadecimal representation
 */
val String?.isHexString get() = this?.matches(HEX_STRING.toMatchRegex()) ?: false

/**
 * check if string match phone number
 */
val String.isPhoneNumber
    get() = matches(PHONE_PATTERN.toMatchRegex())

/**
 * check if string match ip address
 */
val String.isIPAddress
    get() = matches(IP_ADDRESS_PATTERN.toMatchRegex())

/**
 * check if string match web url
 */
val String.isWebUrl
    get() = if (matches(WEB_URL.toMatchRegex())) true else matches(AUTOLINK_WEB_URL.toMatchRegex())

/**
 * check if string match email format
 */
val String.isEmail
    get() = if (matches(EMAIL_PATTERN.toMatchRegex())) true
    else matches(AUTOLINK_EMAIL_ADDRESS.toMatchRegex())
