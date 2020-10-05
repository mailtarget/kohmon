package co.mtarget.kohmon

import java.io.File

fun Collection<String>.isAllExist(): Boolean {
    return this.map { File(it) }.firstOrNull { !it.exists() } == null
}
