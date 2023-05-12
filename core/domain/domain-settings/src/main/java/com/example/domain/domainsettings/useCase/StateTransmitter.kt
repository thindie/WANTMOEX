package com.example.domain.domainsettings.useCase

import com.example.domain.domainsettings.entityContract.SettingsStateTransmitter

private const val INITIAL_COINS_LIST_CAPACITY = 10
internal const val BASIC_CAPACITY = 10
internal const val ADVANCED_CAPACITY = 30
internal const val EXPERT_CAPACITY = 50

private const val TAG_BTC = "BTC"
private const val NEWS_TAG_ETH = "ETH"
private const val NEWS_TAG_XRP = "XRP"
private const val NEWS_TAG_DOGE = "DOGE"
private const val NEWS_TAG_SHIBA = "SHIBA"

internal const val SORT_PRICE = "price"
internal const val SORT_UPDATED = "lastUpdate"
internal const val SORT_TICKER = "toSymbol"
internal const val SORT_PERCENT_DELTA = "percentDelta"

internal val defaultTags: List<String> = listOf(
    TAG_BTC,
    NEWS_TAG_ETH,
    NEWS_TAG_XRP,
    NEWS_TAG_DOGE,
    NEWS_TAG_SHIBA
)

internal val mostPopularTags: List<String> = listOf(
    TAG_BTC,
    NEWS_TAG_ETH,
    NEWS_TAG_XRP,
)

internal data class StateTransmitter(
    override val capacity: Number = INITIAL_COINS_LIST_CAPACITY,
    override val tagsHolder: List<String> = defaultTags,
    override val ticker: String = TAG_BTC,
    override val sortBy: String = SORT_PRICE,
    override val isAsc: Boolean = false,
) : SettingsStateTransmitter
