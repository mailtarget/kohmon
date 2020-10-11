package co.mtarget.kohmon

import co.mtarget.kohmon.string.AUTOLINK_WEB_URL
import co.mtarget.kohmon.string.WEB_URL
import org.junit.Test

class PatternTest {

    @Test
    fun patternTest() {
        val url = "mtarget.co/pricing"
        assert(WEB_URL.toRegex().matches(url))
        assert(AUTOLINK_WEB_URL.toRegex().matches(url))
    }

}