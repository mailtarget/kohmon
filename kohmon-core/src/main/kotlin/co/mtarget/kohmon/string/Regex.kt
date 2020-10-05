package co.mtarget.kohmon.string

/**
 * Regex helper to create regex pattern for checking match string
 */
fun String.toMatchRegex() = "^$this\$".toRegex()

/**
 * Regex helper to create regex pattern for checking contains
 */
fun String.toContainsRegex() = ".*$this.*".toRegex()

/**
 * Regex helper to create regex pattern with ignore case
 */
fun String.toRegexIgnoreCase() = this.toRegex(RegexOption.IGNORE_CASE)
