package co.mtarget.kohmon.date

import java.util.*

fun Date.toCalendar(): Calendar = Calendar.getInstance().apply { time = this@toCalendar }

fun Date.plusDays(days: Int = 1) = toCalendar().plusDays(days).toDate()

fun Date.plusMinutes(minutes: Int = 1) = toCalendar().plusMinutes(minutes).toDate()

fun Date.plusHours(hours: Int = 1) = toCalendar().plusHours(hours).toDate()

fun Date.plusMonths(months: Int = 1) = toCalendar().plusMonths(months).toDate()

fun Date.plusYears(years: Int = 1) = toCalendar().plusYears(years).toDate()

fun Date.plusWeeks(weeks: Int = 1) = toCalendar().plusWeeks(weeks).toDate()

fun Date.dayOfWeek() = toCalendar().get(Calendar.DAY_OF_WEEK)

fun Date.dayOfMonth() = toCalendar().get(Calendar.DAY_OF_MONTH)

fun Date.withTime(hours: Int, minutes: Int = 0, second: Int = 0): Date = toCalendar().apply {
    set(Calendar.HOUR_OF_DAY, hours)
    set(Calendar.MINUTE, minutes)
    set(Calendar.SECOND, second)
    set(Calendar.MILLISECOND, 0)
}.time

fun Date.resetTime() = withTime(0, 0)