package com.example.core.presentation.uisettings

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import com.example.core.designelements.components.screenelements.topbar.ExpandableTopBar
import com.example.core.designelements.components.screenelements.topbar.SortTriggerAvailAble

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UiSettingsBar(
    triggers: List<SortTriggerAvailAble>,
    scrollBehaviors: TopAppBarScrollBehavior,
    title: String,
    onClick: (SortTriggerAvailAble) -> Unit,
) {
    ExpandableTopBar(
        title = title,
        actions = triggers,
        behavior = scrollBehaviors,
        onClick = onClick
    )
}
