package co.mtarget.kohmon.date

import java.util.*

fun Calendar.toDate(): Date = time

fun Calendar.plus(period: Int, value: Int) = this.apply { add(period, value) }
fun Calendar.plusMinutes(value: Int) = plus(Calendar.MINUTE, value)
fun Calendar.plusHours(value: Int) = plus(Calendar.HOUR_OF_DAY, value)
fun Calendar.plusDays(value: Int) = plus(Calendar.DAY_OF_MONTH, value)
fun Calendar.plusWeeks(value: Int) = plus(Calendar.WEEK_OF_YEAR, value)
fun Calendar.plusMonths(value: Int) = plus(Calendar.MONTH, value)
fun Calendar.plusYears(value: Int) = plus(Calendar.YEAR, value)
