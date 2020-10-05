package co.mtarget.kohmon

import org.junit.Test

class LoggingTest {

    private val log = logger(this::class)

    @Test
    fun logTest() {
        assert(log.name == "LoggingTest")
        logDebug { "should not appear" }
        logInfo { "should appear" }
        logWarn { "should appear" }
        logError { "should appear" }
    }

}