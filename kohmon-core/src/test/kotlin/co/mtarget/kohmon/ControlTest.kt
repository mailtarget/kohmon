package co.mtarget.kohmon

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertNull

class ControlTest {

    private val data1 = listOf("a", "b", "3", "4")

    @Test
    fun retryTest() = runBlocking {
        val result1= retryError(3) { data1[it-1].toInt() }
        assert(result1 == 3)
        val result2= tryOrNull { retryNull(2) { data1[it-1].toIntOrNull() } }
        assertNull(result2)
    }

}