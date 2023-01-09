package com.example.thindie.wantmoex.presentation.theme.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.domain.entities.Share
import com.example.thindie.wantmoex.presentation.theme.WANTMOEXTheme

@Composable
fun ShareThing(
    share: Share
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(modifier = Modifier.padding(40.dp), text = share.id)
        }

    }

}

