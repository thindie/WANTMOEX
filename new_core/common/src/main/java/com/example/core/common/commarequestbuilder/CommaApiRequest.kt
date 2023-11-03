package com.example.core.common.commarequestbuilder


private const val COMMA = ","
private const val NETWORK_QUERY_PATTERN_FOR_TAGS = "%s$COMMA"


/**
 * for retrofit: query builder with comma-separated tags
 * dont forget set @GET param 'encoded = true'
 */
fun commaQueryEncodedBuilder(list: Iterable<String>): String {
    var string = ""
    list.forEach {
        string += String.format(NETWORK_QUERY_PATTERN_FOR_TAGS, it)
    }
    return string
}
