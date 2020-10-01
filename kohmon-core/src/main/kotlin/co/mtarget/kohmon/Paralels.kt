package co.mtarget.kohmon

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlin.coroutines.CoroutineContext

// ref. https://stackoverflow.com/questions/45575516/kotlin-process-collection-in-parallel/45794421#45794421
// ref. https://jivimberg.io/blog/2018/05/04/parallel-map-in-kotlin/
suspend fun <A> Collection<A>.forEachParallel(
    context: CoroutineContext = Dispatchers.Default,
    fn: suspend (A) -> Unit
): Unit = coroutineScope {
    map { async(context) { fn(it) } }.forEach { it.await() }
}

suspend fun <A> Iterable<A>.forEachParallelIterable(
    context: CoroutineContext = Dispatchers.Default,
    fn: suspend () -> Unit
): Unit = coroutineScope {
    map { async(context) { fn() } }.forEach { it.await() }
}

suspend fun <A> Collection<A>.forEachParallelIndexed(
    context: CoroutineContext = Dispatchers.Default,
    fn: suspend (Int, A) -> Unit
): Unit = coroutineScope {
    mapIndexed { idx, data -> async(context) { fn(idx, data) } }.forEach { it.await() }
}