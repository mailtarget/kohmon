package co.mtarget.kohmon

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.KClass
import kotlin.reflect.KFunction
import kotlin.reflect.KParameter
import kotlin.reflect.KType
import kotlin.reflect.full.callSuspendBy
import kotlin.reflect.full.extensionReceiverParameter
import kotlin.reflect.full.instanceParameter
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure

fun KType.isCollection() = Collection::class.isAssignableFrom(this)

fun KType.isArray() = this.erasedType().isArray

fun KType.isArrayOrCollection() = this.isArray() || this.isCollection()

fun KType.isMap() = Map::class.isAssignableFrom(this)

fun KType.isPair() = Pair::class.isAssignableFrom(this)

fun KType.isMapOrPair() = this.isMap() || this.isPair()

fun KType.isEnum() = this.erasedType().isEnum

fun KType.isVoid() = this.toString() == "kotlin.Unit"

fun KType.erasedType(): Class<out Any> = this.jvmErasure.java

fun <T : Any> KClass<T>.isAssignableFrom(other: KType): Boolean {
    if (this.java == other.javaType) return true
    return this.java.isAssignableFrom(other.erasedType())
}

fun KType.isAssignableFrom(other: KClass<*>): Boolean {
    return (this.erasedType()).isAssignableFrom(other.java)
}

fun <R> KFunction<R>.callNamed(params: Map<String, Any?>, self: Any? = null, extSelf: Any? = null): R {
    val map = params.entries.mapTo(ArrayList()) { entry ->
        parameters.find { name == entry.key }!! to entry.value
    }
    if (self != null) map += instanceParameter!! to self
    if (extSelf != null) map += extensionReceiverParameter!! to extSelf
    return callBy(map.toMap())
}

suspend fun <R> KFunction<R>.invoke(params: MutableMap<KParameter, Any?>, self: Any? = null, extSelf: Any? = null): R {
    if (self != null) params[instanceParameter!!] = self
    if (extSelf != null) params[extensionReceiverParameter!!] = extSelf
    return callSuspendBy(params)
}

fun KType.isPrimitive(): Boolean {
    return when {
        isAssignableFrom(String::class) ||
                isAssignableFrom(Int::class) ||
                isAssignableFrom(Double::class) ||
                isAssignableFrom(Float::class) ||
                isAssignableFrom(Long::class) ||
                isAssignableFrom(ByteArray::class) ||
                isAssignableFrom(Byte::class)
                || isAssignableFrom(Boolean::class) -> true
        else -> false
    }
}

fun KType.isPrimitiveLike(): Boolean {
    return when {
        isAssignableFrom(String::class) ||
                isAssignableFrom(Short::class) ||
                isAssignableFrom(BigDecimal::class) ||
                isAssignableFrom(BigInteger::class) ||
                isAssignableFrom(Number::class) ||
                isAssignableFrom(CharArray::class) ||
                isAssignableFrom(Char::class)
                || isAssignableFrom(Short::class) -> true
        else -> false
    }
}
