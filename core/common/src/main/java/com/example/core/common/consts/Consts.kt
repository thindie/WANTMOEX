package com.example.core.common.consts

import kotlinx.coroutines.flow.SharingStarted

private const val TIMEOUT_IN_MILLIS = 5000L

val sharingStarted = SharingStarted.WhileSubscribed(TIMEOUT_IN_MILLIS)

const val REFRESHING_RATE = 60_000L
const val SHARE = "share"
const val BROWSE = "browse"
