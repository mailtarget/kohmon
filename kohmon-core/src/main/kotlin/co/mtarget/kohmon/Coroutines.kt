package co.mtarget.kohmon

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

/**
 * run suspended function block using io dispatcher
 */
suspend fun <T> runIO(block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.IO, block)

/**
 * run suspended function block using default dispatcher
 */
suspend fun <T> runDefault(block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.Default, block)

/**
 * Coroutine Dispatcher from Executors
 */
private val threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())
private val singleThread = Executors.newSingleThreadExecutor()

val Dispatchers.Background: CoroutineDispatcher get() = threadPool.asCoroutineDispatcher()
val Dispatchers.SingleThread: CoroutineDispatcher get() = singleThread.asCoroutineDispatcher()

/**
 * create CoroutineJob from Dispatcher
 */
fun CoroutineDispatcher.createJob(): CoroutineJob = CoroutineJob(this)

fun CoroutineDispatcher.start(block: suspend () -> Unit): Job = createJob().start(block)

open class CoroutineJob(private val dispatcher: CoroutineDispatcher = Dispatchers.Default): CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + dispatcher

    open fun stop() { job.cancel()  }

    fun start(block: suspend () -> Unit): Job = launch { block() }
}
