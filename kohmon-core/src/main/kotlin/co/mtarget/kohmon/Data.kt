package co.mtarget.kohmon

typealias MapData = HashMap<String, Any?>

data class Counter<T> constructor(val data: T, val count: Int? = 0)

open class ResponseData<T: Any?>(val data: T, val code: Int = 200, val message: String = "")

