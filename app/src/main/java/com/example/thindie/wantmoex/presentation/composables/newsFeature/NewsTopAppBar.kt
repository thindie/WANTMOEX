package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.R

@Composable

fun NewsTopAppBar(title: String, onClick: () -> Unit) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(64.dp)

    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 16.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.Start
            ) {
                IconButton(onClick = onClick) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menu")
                }
            }
            Column(
                modifier = Modifier.fillMaxSize(0.9f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = title, style = MaterialTheme.typography.headlineSmall)
            }
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(painter = painterResource(id = R.drawable.crypto), contentDescription = null)
            }
        }


    }
}
