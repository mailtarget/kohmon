package co.mtarget.kohmon

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.KClass

fun logger(clz: KClass<*>): Logger = logger(clz.simpleName)

fun logger(name: String?): Logger = LoggerFactory.getLogger(name)

fun logInfo(message: () -> String) = getFunctionLogger(message).info(message())
fun logDebug(message: () -> String) = getFunctionLogger(message).debug(message())
fun logWarn(message: () -> String) = getFunctionLogger(message).warn(message())
fun logError(message: () -> String) = getFunctionLogger(message).error(message())

private fun getFunctionLogger(anchorFunction: () -> String): Logger {
    fun getLoggerName(): String {
        val anchorClassName = anchorFunction.javaClass.name
        val lastDollarIndex = anchorClassName.lastIndexOf('$')
        if (lastDollarIndex == -1 ) return anchorClassName
        val prevLastDollarIndex = anchorClassName.lastIndexOf('$', startIndex = lastDollarIndex - 1)
        if (prevLastDollarIndex == -1) return anchorClassName
        return anchorClassName.substring(0, prevLastDollarIndex) + '.' +
                anchorClassName.substring(prevLastDollarIndex + 1, lastDollarIndex)
    }
    return LoggerFactory.getLogger(getLoggerName())
}
