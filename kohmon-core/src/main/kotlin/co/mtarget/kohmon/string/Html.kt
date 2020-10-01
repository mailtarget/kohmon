package co.mtarget.kohmon.string

import java.util.regex.Pattern

// adapted from post by Phil Haack and modified to match better
const val HTML_TAG_START_PATTERN = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)\\>"
const val HTML_TAG_END_PATTERN = "\\</\\w+\\>"
const val HTML_TAG_SELF_CLOSING_PATTERN = "\\<\\w+((\\s+\\w+(\\s*\\=\\s*(?:\".*?\"|'.*?'|[^'\"\\>\\s]+))?)+\\s*|\\s*)/\\>"
const val HTML_ENTITY_PATTERN = "&[a-zA-Z][a-zA-Z0-9]+;"

val htmlPattern = Pattern.compile("($HTML_TAG_START_PATTERN.*$HTML_TAG_END_PATTERN)|($HTML_TAG_SELF_CLOSING_PATTERN)|($HTML_ENTITY_PATTERN)", Pattern.DOTALL)!!

fun String?.isHtml(): Boolean {
    var ret = false
    if (this != null) {
        ret = htmlPattern.matcher(this).find()
    }
    return ret
}

// FIXME: this is just a fast fix for html with no body
fun String?.isHtmlContainsBody(): Boolean {
    var ret = false
    if (this != null && this.contains("<body") && this.contains("</body")) {
        ret = htmlPattern.matcher(this).find()
    }
    return ret
}