package co.mtarget.kohmon.date

import co.mtarget.kohmon.tryOrNull
import java.text.SimpleDateFormat
import java.util.*

const val TIME_SIMPLE_FORMAT = "HH:mm"
const val DATE_SIMPLE_FORMAT = "yyyy-MM-dd"
const val DATE_TIME_SIMPLE_FORMAT = "$DATE_SIMPLE_FORMAT $TIME_SIMPLE_FORMAT"
const val DATE_READABLE_FORMAT = "dd MMM yyyy"

/**
 * convert string to SimpleDateFormat
 */
fun String.toDateFormat(): SimpleDateFormat = SimpleDateFormat(this)

/**
 * convert string to Date
 */
fun String.toDate(pattern: String = DATE_SIMPLE_FORMAT): Date =
        pattern.toDateFormat().parse(this)

/**
 * convert string to Date, when there is exceptions return null
 */
fun String.toDateOrNull(pattern: String = DATE_SIMPLE_FORMAT): Date? =
        tryOrNull { toDate(pattern) }

/**
 * convert date to String with certain format
 */
fun Date.format(pattern: String = DATE_TIME_SIMPLE_FORMAT): String = pattern.toDateFormat().format(this)
