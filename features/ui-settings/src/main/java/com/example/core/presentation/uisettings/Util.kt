package com.example.core.presentation.uisettings

import com.example.core.designelements.R
import com.example.core.designelements.components.screenelements.topbar.SortTriggerAvailAble
import com.example.core.designelements.icons.ShortHandedIcons

/**
 *Implemented [SortTriggerAvailAble] for ExpandedTopBar
 * */


internal data class CapacityTrigger(
    override val icon: Int,
    override val title: Int,
    override val isSelected: Boolean = false,
    override val id: String,
    val capacity: Capacity,
) : SortTriggerAvailAble

internal data class SortTrigger(
    override val icon: Int,
    override val title: Int,
    override val isSelected: Boolean = false,
    override val id: String,
    val sorter: ListSorters,
) : SortTriggerAvailAble

internal data class TagTrigger(
    override val icon: Int,
    override val title: Int,
    override val isSelected: Boolean = false,
    override val id: String,
    val tag: Tags,
) : SortTriggerAvailAble

internal val capacityBasic = CapacityTrigger(
    icon = ShortHandedIcons.capacityBasic,
    title = R.string.capacity_basic,
    id = "capacityBasic",
    capacity = Capacity.Basic
)

internal val capacityAdvanced = CapacityTrigger(
    icon = ShortHandedIcons.capacityAdvanced,
    title = R.string.capacity_advanced,
    id = "capacityAdvanced",
    capacity = Capacity.Advanced
)

internal val capacityExpert = CapacityTrigger(
    icon = ShortHandedIcons.capacityExpert,
    title = R.string.capacity_expert,
    id = "capacityExpert",
    capacity = Capacity.Expert
)

internal val tagsAll = TagTrigger(
    icon = ShortHandedIcons.tagsAll,
    title = R.string.news_tags_all,
    id = "tagsAll",
    tag = Tags.All
)

internal val tagsPopular = TagTrigger(
    icon = ShortHandedIcons.tagsPopular,
    title = R.string.news_tags_popular,
    id = "tagsPopular",
    tag = Tags.Popular
)


internal val sortPrice = SortTrigger(
    icon = ShortHandedIcons.priceUp,
    title = R.string.sort_price,
    id = "priceUp",
    sorter = ListSorters.Price
)

internal val sortPriceDesc = SortTrigger(
    icon = ShortHandedIcons.priceDown,
    title = R.string.sort_price_desc,
    id = "priceDown",
    sorter = ListSorters.PriceDesc
)

internal val sortTicker = SortTrigger(
    icon = ShortHandedIcons.tickerNatural,
    title = R.string.sort_ticker,
    id = "tickerNatural",
    sorter = ListSorters.Ticker
)

internal val sortTickerDesc = SortTrigger(
    icon = ShortHandedIcons.tickerReversed,
    title = R.string.sort_ticker_desc,
    id = "tickerReversed",
    sorter = ListSorters.TickerDesc
)

internal val sortByUpdate = SortTrigger(
    icon = ShortHandedIcons.timeNatural,
    title = R.string.sort_update,
    id = "timeNatural",
    sorter = ListSorters.Update
)

internal val sortByUpdateDesc = SortTrigger(
    icon = ShortHandedIcons.timeReverse,
    title = R.string.sort_update_desc,
    id = "timeReversed",
    sorter = ListSorters.UpdateDesc
)

internal val sortGrow = SortTrigger(
    icon = ShortHandedIcons.percentUp,
    title = R.string.sort_grow,
    id = "percentUp",
    sorter = ListSorters.Grow
)


internal val sortGrowDesc = SortTrigger(
    icon = ShortHandedIcons.percentDown,
    title = R.string.sort_grow_desc,
    id = "percentDown",
    sorter = ListSorters.GrowDesc
)

internal val listSortSelectionWithoutCapacity = listOf(
    sortPrice,
    sortPriceDesc,
    sortGrow,
    sortGrowDesc,
    sortByUpdate,
    sortByUpdateDesc,
    sortTicker,
    sortTickerDesc,
)
internal val newsTagsSelection = listOf(tagsAll, tagsPopular)

internal val fullListSortSelection = listOf(
    sortPrice,
    sortPriceDesc,
    sortGrow,
    sortGrowDesc,
    sortByUpdate,
    sortByUpdateDesc,
    sortTicker,
    sortTickerDesc,
    capacityBasic,
    capacityAdvanced,
    capacityExpert
)
