package co.mtarget.kohmon.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.util.*

fun LocalDateTime.toDate(): Date =
    Date.from(atZone(ZoneId.systemDefault()).toInstant())

fun Date.toLocalDateTime(): LocalDateTime =
    toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime()

fun Date.toLocalDate(): LocalDate =
    this.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()

fun LocalDate.toDate(): Date =
    Date.from(atStartOfDay().atZone(ZoneId.systemDefault()).toInstant())