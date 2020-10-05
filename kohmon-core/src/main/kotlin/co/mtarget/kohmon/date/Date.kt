package co.mtarget.kohmon.date

import java.util.*

fun Date.toCalendar(): Calendar = Calendar.getInstance().apply { time = this@toCalendar }

fun Date.plusDays(days: Int = 1) = plus(Calendar.DAY_OF_YEAR, days)

fun Date.plusHours(hours: Int = 1) = plus(Calendar.HOUR_OF_DAY, hours)

fun Date.plusMonths(months: Int = 1) = plus(Calendar.MONTH, months)

fun Date.plusYears(years: Int = 1) = plus(Calendar.YEAR, years)

fun Date.plusWeeks(weeks: Int = 1) = plus(Calendar.WEEK_OF_YEAR, weeks)

fun Date.plus(period: Int, num: Int): Date = this.toCalendar().apply { add(period, num) }.time

fun Date.get(period: Int): Int = this.toCalendar().get(period)

fun Date.dayOfWeek() = get(Calendar.DAY_OF_WEEK)

fun Date.dayOfMonth() = get(Calendar.DAY_OF_MONTH)

fun Date.withTime(hours: Int, minutes: Int): Date = toCalendar().apply {
    set(Calendar.HOUR_OF_DAY, hours)
    set(Calendar.MINUTE, minutes)
}.time

fun Date.resetTime() = withTime(0, 0)