package co.mtarget.kohmon

fun Throwable.getStackTraceAsString(): String {
    return (if (!this.localizedMessage.isNullOrBlank()) this.localizedMessage.plus("\n") else "")
        .plus(this.stackTrace.joinToString("\n") { it.toString() })
}

