package co.mtarget.kohmon

import org.junit.Test
import kotlin.test.assertNotNull

class ResourcesTest {

    @Test
    fun resourceTest() {
        val resName = "read.txt"
        val text = getResource(resName)
        assert(text != null)
        assert(text?.equals("okay") ?: false)
    }

    @Test
    fun resourceStreamTest() {
        val resName = "read.txt"
        val inputString = getResourceAsStream(resName)
        assertNotNull(inputString)
        val text = inputString.bufferedReader().readLine()
        assert(text?.equals("okay") ?: false)
    }
}


