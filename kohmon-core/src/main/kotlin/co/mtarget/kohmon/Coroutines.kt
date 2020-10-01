package co.mtarget.kohmon

import kotlinx.coroutines.*
import java.util.concurrent.Executors
import kotlin.coroutines.CoroutineContext

suspend fun <T> runIO(block: suspend CoroutineScope.() -> T): T = withContext(Dispatchers.IO, block)

/**
 * create coroutine job from different executors
 */
private val threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())

private val singleThread = Executors.newSingleThreadExecutor()

val Dispatchers.Background: CoroutineDispatcher get() = threadPool.asCoroutineDispatcher()

fun CoroutineDispatcher.createJob(): CoroutineJob = CoroutineJob(this)

fun CoroutineDispatcher.run(block: suspend () -> Unit): Job = CoroutineJob(this).start(block)

open class CoroutineJob(private val dispatcher: CoroutineDispatcher = Dispatchers.Default): CoroutineScope {
    private val job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + dispatcher

    open fun stop() { job.cancel() }

    fun start(block: suspend () -> Unit): Job = launch { block() }
}