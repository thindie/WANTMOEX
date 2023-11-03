package com.example.core.designelements.components.screenelements.buttonsbar

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.core.designelements.components.screenelements.ButtonFiller
import com.example.core.designelements.components.util.BodyText

@Composable
fun OutlinedButtonsBar(list: List<ButtonFiller>, modifier: Modifier = Modifier) {
    LazyRow(
        modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        items(list) { filler ->
            OutlinedButton(onClick = { filler.onClick(filler.label) }) {
                filler.label.BodyText()
            }
            Spacer(modifier = Modifier.padding(start = 8.dp))
        }
    }
}
