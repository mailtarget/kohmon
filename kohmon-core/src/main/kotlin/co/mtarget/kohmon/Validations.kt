package co.mtarget.kohmon

const val VALIDATION_MESSAGE = "invalid submitted data value"

fun <T> T.validate(message: String = VALIDATION_MESSAGE, block: T.() -> Boolean): T {
    require(block(this)) { message }
    return this
}

fun <T> T.validateOrNull(block: T.() -> Boolean): T? {
    return if (block(this)) this
    else null
}