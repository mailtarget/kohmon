package co.mtarget.kohmon

import co.mtarget.kohmon.date.*
import org.junit.Test
import java.time.format.DateTimeFormatter
import kotlin.test.assertNotNull

class DateTest {

    @Test
    fun commonTest() {
        val dateString1 = "14 Oct 2020"
        val date1 = dateString1.toDateOrNull(DATE_READABLE_FORMAT)
        val date2 = dateString1.toDateOrNull(DATE_TIME_SIMPLE_FORMAT)
        assert(date1 != null)
        assert(date2 == null)
    }

    @Test
    fun localDateTest() {
        val dateString1 = "14 Oct 2020"
        val date1 = dateString1.toDateOrNull(DATE_READABLE_FORMAT)
        val localDate1 = date1?.toLocalDate()
        val localDateTime1 = date1?.toLocalDateTime()
        val zonedDateTime1 = date1?.toZonedDateTime()
        assertNotNull(localDate1)
        assertNotNull(localDateTime1)
        assertNotNull(zonedDateTime1)
        assert(zonedDateTime1.format(DateTimeFormatter.ISO_ZONED_DATE_TIME) != null)
        assert(localDate1.toDate().format() == date1.format())
        assert(localDateTime1.toDate().format() == date1.format())
        assert(zonedDateTime1.toDate().format() == date1.format())
    }

    @Test
    fun formatTest() {
        val dateString1 = "2020-10-14"
        val dateString2 = "2020-10-15"
        val dateString3 = "2020-09-15"
        val dateString4 = "2020-10-7"
        val date1 = dateString1.toDate(DATE_SIMPLE_FORMAT).resetTime()
        val date2 = dateString2.toDate(DATE_SIMPLE_FORMAT)
        val date3 = dateString3.toDate(DATE_SIMPLE_FORMAT)
        val date4 = dateString4.toDate(DATE_SIMPLE_FORMAT)
        assert(date1.plusDays(1).format() == date2.format())
        assert(date2.plusMonths(-1).format() == date3.format())
        assert(date1.plusWeeks(-1).format() == date4.format())
        assert(date1.plusYears().dayOfMonth() == 14)
        assert(date1.dayOfWeek() == date4.dayOfWeek())
        assert(date2.dayOfMonth() == date3.dayOfMonth())
    }

}