package com.example.thindie.wantmoex.data.mappers

import java.text.SimpleDateFormat
import java.util.*

fun getCalculateDate(): String{
    val calendar = Calendar.getInstance()
    val simpleDateFormat = SimpleDateFormat(PATTERN, Locale.UK)
    calendar.add(Calendar.DAY_OF_YEAR, COUPLE_OF_DAYS)
    return simpleDateFormat.format(Date(calendar.timeInMillis))
}

private const val COUPLE_OF_DAYS = -10
private const val PATTERN = "yyyy-MM-dd"