package co.mtarget.kohmon.string

fun String.toBoolean(): Boolean {
    return this != ""
            && (this.equals("TRUE", ignoreCase = true)
            || this.equals("Y", ignoreCase = true)
            || this.equals("YES", ignoreCase = true))
}

