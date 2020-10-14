package co.mtarget.kohmon

import co.mtarget.kohmon.string.isEmail
import org.junit.Test
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ValidationTest {

    data class Contact(val email: String, val name: String = "", val age: Int = 18)
    val contact = Contact("masasdn@gmail.com")

    @Test
    fun validTest() {
        val validContact: Contact? = contact
                .validate { email.isEmail() }
                .validate { age >= 18 }

        assertNotNull(validContact)

        val invalidContact: Contact? = contact
                .validateOrNull { email.isEmpty() }

        assertNull(invalidContact)
    }

    @Test(expected = IllegalArgumentException::class)
    fun invalidTest() {
        contact.validate { name.isNotEmpty() }
    }
}