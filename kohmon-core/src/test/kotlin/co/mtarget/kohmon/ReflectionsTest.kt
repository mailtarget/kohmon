package co.mtarget.kohmon

import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertNotNull

class ReflectionsTest {

    private val listCounter = Counter(listOf("a", "b", "c"))
    private val responseData = ResponseData<MapData>(hashMapOf("foo" to "bar"))

    class Data (val data: MapData) {
        fun print() {
            println("oke")
        }
    }

    @Test
    fun counterTest() {
        assert(listCounter.count == 3)
    }

    @Test
    fun reflectionTest() {
        val count = Counter::class.property("count")
        assertNotNull(count)
        assert(count.returnType.isPrimitiveLike() ?: false)
        val data = Counter::class.property("data")
        assertNotNull(data)
        assert(data.returnType.isArrayOrCollection())
        val map = Data::class.property("data")
        assertNotNull(map)
        assert(map.returnType.isMapOrPair())
        assertFalse(map.returnType.isEnum())
        val func = Data::class.function("print")
        assertNotNull(func)
        assert(func.returnType.isVoid())
    }

    @Test
    fun suspendTest() = runBlocking {
        val map = responseData::class.property("data")
        assertNotNull(map)
        val function = map::class.function("toString")
        assert(function != null)
        val str1 = function?.callNamed(mapOf(), map)
        val str2 = function?.invoke(hashMapOf(), map)
        assert(str1 != null)
        assert(str2 != null)
    }

}