package co.mtarget.kohmon

import co.mtarget.kohmon.string.toBoolean
import org.junit.Test
import java.util.*
import kotlin.test.assertFalse

class NullableTest {

    @Test
    fun nullableTest() {
        val str1 = ""
        val int1 = str1.toIntOrNull().default(1)
        assert(int1 == 1)
        val int2 = int1.orAssign(2)
        assert(int2 == 2)
        val int3 = int1.orAssign(null)
        assert(int3 == 1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun requiredTest() {
        val str1 = ""
        str1.toIntOrNull().required()
    }

    @Test(expected = IllegalArgumentException::class)
    fun illegalTest() {
        val str1 = "false"
        str1.toBoolean().orIllegal()
    }

    @Test
    fun nullStringTest() {
        val str1: String? = null
        val str2 = str1.default("1")
        val str3 = str2.orAssign(null)
        assert(str2 == "1")
        assert(str3 == "1")
    }

    @Test
    fun optionalTest() {
        val str1 = ""
        val int1: Optional<Int> = str1.toIntOrNull().toOptional()
        assertFalse(int1.isPresent)
        val int2: Int = int1.toNullable().default(1)
        assert(int2 == 1)
    }
}