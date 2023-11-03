package com.example.core.presentation.uisettings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.core.designelements.components.screenelements.topbar.SortTriggerAvailAble
import com.example.domain.domainsettings.contract.CoinsCapacitySetter
import com.example.domain.domainsettings.contract.NewsTagsSetter
import com.example.domain.domainsettings.contract.SortListTrigger
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SettingsScreenViewModel @Inject constructor(
    private val sortTrigger: SortListTrigger,
    private val setCapacity: CoinsCapacitySetter,
    private val setTags: NewsTagsSetter,
) : ViewModel() {

    private var _currentTrigger by mutableStateOf("")
    val activatedTriggerID: String
        get() = _currentTrigger

    private var _currentExpandedState by mutableStateOf(false)
    val isExpanded: Boolean
        get() = _currentExpandedState

    fun applyTriggerSortAction(triggerAvailAble: SortTriggerAvailAble) {
        _currentTrigger = triggerAvailAble.id
        when (triggerAvailAble) {
            is SortTrigger -> confirmActualListSort(triggerAvailAble)
            is CapacityTrigger -> confirmActualCapacity(triggerAvailAble)
            is TagTrigger -> confirmActualTags(triggerAvailAble)
        }
    }

    fun onTriggerExpand() {
        _currentExpandedState = !_currentExpandedState
    }

    private fun confirmActualCapacity(capacityTrigger: CapacityTrigger) {
        when (capacityTrigger.capacity) {
            Capacity.Basic -> setCapacity.basicCapacity()
            Capacity.Advanced -> setCapacity.advancedCapacity()
            Capacity.Expert -> setCapacity.expertCapacity()
        }
    }

    private fun confirmActualTags(tagsTrigger: TagTrigger) {
        when (tagsTrigger.tag) {
            Tags.Popular -> setTags.setPopular()
            Tags.All -> setTags.setAll()
        }
    }

    private fun confirmActualListSort(listSortTrigger: SortTrigger) {
        when (listSortTrigger.sorter) {
            ListSorters.Price -> sortTrigger.byPrice()
            ListSorters.PriceDesc -> sortTrigger.byPriceDesc()
            ListSorters.Ticker -> sortTrigger.byTicker()
            ListSorters.TickerDesc -> sortTrigger.byTickerDesc()
            ListSorters.Update -> sortTrigger.byLastUpdate()
            ListSorters.UpdateDesc -> sortTrigger.byLastUpdateDesc()
            ListSorters.Grow -> sortTrigger.byPercentageDelta()
            ListSorters.GrowDesc -> sortTrigger.byPercentageDeltaDesc()
        }
    }
}

enum class ListSorters {
    Price, PriceDesc, Ticker,
    TickerDesc, Update, UpdateDesc,
    Grow, GrowDesc
}

enum class Tags {
    All, Popular
}

enum class Capacity {
    Basic, Advanced, Expert
}
