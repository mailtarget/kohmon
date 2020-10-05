package co.mtarget.kohmon.date

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.util.*

val timeZoneDefault = ZoneId.systemDefault()

fun LocalDateTime.toDate(zoneId: ZoneId = timeZoneDefault): Date =
    Date.from(atZone(zoneId).toInstant())

fun Date.toLocalDateTime(zoneId: ZoneId = timeZoneDefault): LocalDateTime =
    toInstant().atZone(zoneId).toLocalDateTime()

fun Date.toLocalDate(zoneId: ZoneId = timeZoneDefault): LocalDate =
    this.toInstant().atZone(zoneId).toLocalDate()

fun LocalDate.toDate(zoneId: ZoneId = timeZoneDefault): Date =
    Date.from(atStartOfDay().atZone(zoneId).toInstant())

fun Date.toZonedDateTime(zoneId: ZoneId = timeZoneDefault): ZonedDateTime =
        ZonedDateTime.ofInstant(toInstant(), zoneId)

fun ZonedDateTime.toDate(): Date = Date.from(this.toInstant())