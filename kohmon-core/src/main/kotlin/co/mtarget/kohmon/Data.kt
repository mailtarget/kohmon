package co.mtarget.kohmon

typealias MapData = HashMap<String, Any?>

open class Counter<T: Collection<*>>(val data: T?, val count: Int = data?.size ?: 0)

open class ResponseData<T: Any?>(val data: T, val code: Int = 200, val message: String = "")

