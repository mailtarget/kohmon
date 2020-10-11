package co.mtarget.kohmon

import java.math.BigDecimal
import java.math.BigInteger
import kotlin.reflect.*
import kotlin.reflect.full.*
import kotlin.reflect.jvm.javaType
import kotlin.reflect.jvm.jvmErasure

fun KClass<*>.property(name: String): KProperty<*>? = this.memberProperties.firstOrNull { it.name == name }

fun KClass<*>.function(name: String): KFunction<*>? = this.memberFunctions.firstOrNull { it.name == name }

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
        isPrimitive() ||
                isAssignableFrom(Short::class) ||
                isAssignableFrom(BigDecimal::class) ||
                isAssignableFrom(BigInteger::class) ||
                isAssignableFrom(Number::class) ||
                isAssignableFrom(CharArray::class) ||
                isAssignableFrom(Char::class)  -> true
        else -> false
    }
}
