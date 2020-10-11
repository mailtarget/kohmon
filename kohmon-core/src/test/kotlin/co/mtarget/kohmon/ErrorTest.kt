package co.mtarget.kohmon

import org.junit.Test

class ErrorTest  {

    @Test
    fun errorTest() {
        val message = "test illegal"
        try {
            illegal(message)
        } catch (t: Throwable) {
            val str = t.getStackTraceAsString()
            assert(str.contains(message))
        }
    }

}