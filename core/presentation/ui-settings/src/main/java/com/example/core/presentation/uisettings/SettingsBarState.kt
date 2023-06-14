package com.example.core.presentation.uisettings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import com.example.core.designelements.components.screenelements.topbar.SortTriggerAvailAble
import com.example.core.designelements.cryptoroutes.CryptoRoutes

@Composable
fun rememberSettingsBarState(route: String, id: String): SettingsBarState {
    return remember(route, id) {
        SettingsBarState(route, id)
    }
}

@Stable
class SettingsBarState(private val route: String, private val id: String) {
    val currentTab: List<SortTriggerAvailAble>
        @Composable get() = when (route) {
            CryptoRoutes.news -> {
                newsTagsSelection.map { tagTrigger ->
                    if (tagTrigger.id == id) {
                        tagTrigger.copy(isSelected = true)
                    } else {
                        tagTrigger.copy(isSelected = false)
                    }
                }
            }
            CryptoRoutes.coinsRoute -> {
                fullListSortSelection.map { genericType ->
                    when (genericType) {
                        is CapacityTrigger -> {
                            if (genericType.id == id) {
                                genericType.copy(isSelected = true)
                            } else {
                                genericType.copy(isSelected = false)
                            }
                        }
                        is SortTrigger -> {
                            if (genericType.id == id) {
                                genericType.copy(isSelected = true)
                            } else {
                                genericType.copy(isSelected = false)
                            }
                        }
                        else -> {
                            throw IllegalArgumentException(
                                "Unexpected type of 'SortTriggerAvailAble'" +
                                        " please check inheritance "
                            )
                        }
                    }
                }
            }
            CryptoRoutes.favoritesRoute -> {
                listSortSelectionWithoutCapacity.map { favoriteSortTrigger ->
                    if (favoriteSortTrigger.id == id) {
                        favoriteSortTrigger.copy(isSelected = true)
                    } else {
                        favoriteSortTrigger.copy(isSelected = false)
                    }
                }
            }
            CryptoRoutes.coinRoute -> {
                emptyList()
            }
            else -> {
                emptyList()
            }
        }
    val title: String
        @Composable get() = route
}
