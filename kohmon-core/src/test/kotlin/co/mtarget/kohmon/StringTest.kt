package co.mtarget.kohmon

import co.mtarget.kohmon.string.*
import org.junit.Test
import kotlin.test.assertFalse

class StringTest {

    @Test
    fun checking() {
        assert("sasafdsfd".isAlphabetic())
        assert("sasafdsfd".isAlphanumeric())
        assert("sasafd121232".isContainLetters())
        assert("sasafd121232".isContainNumbers())
        assertFalse("sasafd121232".isNumber())
        assert("0121221".isNumber())
        assert("mm_m.2121@mtd-sds.online.id".isEmail())
        assert("asasdaa.com/sasas?sasas=sasas".isWebUrl())
        assert("192.168.0.1".isIPAddress())
        assert("+62110".isPhoneNumber())
    }

    @Test
    fun base64Test() {
        val str = "ini"
        val base64 = str.base64Encoded()
        assert(base64.base64Decoded() == str)
    }

    @Test
    fun stringHtmlTest() {
        val str = "<article><div><h1>title</ht></div></article>"
        assert(str.isHtml())
        assertFalse(str.isHtmlContainsBody())
    }

    @Test
    fun randomsTest() {
        randomTest(RandomLowercase)
        randomTest(RandomString)
    }

    @Test
    fun transformTest() {
        assert("true".toBoolean())
        assertFalse("false".toBoolean())
        assert("a,b,c,d,e".splitComma().size == 5)
        assert("a,b,c,d,e".countChar('a') == 1)
        assert("ini bukan roti".capitalizeFully() == "Ini Bukan Roti")
        assert("ini bukan roti".camelCased() == "iniBukanRoti")
        assert("ini bukan roti".getMostChar() == 'i')
    }

    @Test
    fun regexTest() {
        val input1 = "OkeGuys"
        val regex1 = "[a-z]+"
        assertFalse(input1.matches(regex1.toRegex()))
        assertFalse(input1.matches(regex1.toMatchRegex()))
        assert(input1.matches(regex1.toContainsRegex()))
        assert(input1.matches(regex1.toRegexIgnoreCase()))
    }

    private fun randomTest(randoms: Randoms) {
        val set = hashSetOf<String>()
        repeat(100) {
            val str = randoms.next()
            assert(str.length == 16)
            assertFalse(set.contains(str))
            set.add(str)
        }
    }

}