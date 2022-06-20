package co.mtarget.kohmon

import java.util.*

/**
 * throw illegal argument exception, if value is null
 */
fun <T> T?.required(message: String = "required value, must not be null"): T = this ?: illegal(message)

/**
 * throw illegal argument exception, if boolean expression is false
 */
fun Boolean?.orIllegal(message: String = "required value must not be null"): Boolean {
    if (this != true) illegal(message)
    return this
}

/**
 * set default non null value for nullable component
 */
infix fun <T> T?.default(value: T): T = this ?: value

/**
 * set default non empty value for nullable string
 */
infix fun String?.default(str: String) = if (this.isNullOrEmpty()) str else this

/**
 * replace value when parameter is not null
 */
infix fun <T> T?.orAssign(value: T?): T? = value ?: this

/**
 * replace value when parameter is not null
 */
infix fun String?.orAssign(str: String?): String? = if (str.isNullOrEmpty()) this else str

/**
 * from java 8 Optional<T> kotlin's nullable
 */
fun <T> Optional<T>.toNullable(): T? = orElse(null)

/**
 * from kotlin's nullable to java 8 Optional<T>
 */
fun <T : Any> T?.toOptional(): Optional<T> = Optional.ofNullable(this)