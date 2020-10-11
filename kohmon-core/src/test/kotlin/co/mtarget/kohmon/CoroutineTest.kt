package co.mtarget.kohmon

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.util.concurrent.atomic.AtomicInteger

class CoroutineTest {

    @Test
    fun coroutineTest() = runBlocking {
        val job1 = Dispatchers.Background.start {
            assert(getThreadName().contains("pool"))
        }
        val job2 = Dispatchers.SingleThread.start {
            assert(getThreadName().contains("pool"))
        }
        val coroutineJob1 = Dispatchers.Unconfined.createJob()
        val job3 = with(coroutineJob1) {
            start {
                assert(getThreadName().contains("main"))
                delay(2000)
            }
        }
        runIO {
            assert(getThreadName().contains("Default"))
        }
        runDefault {
            assert(getThreadName().contains("Default"))
        }
        delay(1000)
        assert(job1.isCompleted)
        assert(job2.isCompleted)

        coroutineJob1.stop()
        assert(job3.isCancelled)
    }

    @Test
    fun parallelTest() = runBlocking {
        val data = listOf("1", "2", "3")
        val counter1 = AtomicInteger()
        data.forEachParallel {
            counter1.incrementAndGet()
        }
        val counter2 = AtomicInteger()
        data.forEachParallelIterable {
            counter2.incrementAndGet()
        }
        val counter3 = AtomicInteger()
        data.forEachParallelIndexed { i, _ ->
            counter3.addAndGet(i+1)
        }
        assert(counter1.get() == 3)
        assert(counter2.get() == 3)
        assert(counter3.get() == 6)
    }

}
