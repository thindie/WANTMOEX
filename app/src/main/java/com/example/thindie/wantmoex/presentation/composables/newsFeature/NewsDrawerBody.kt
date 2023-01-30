package com.example.thindie.wantmoex.presentation.composables.newsFeature

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

private val values = PaddingValues(start = 16.dp, top = 8.dp, bottom = 8.dp)

@Composable
fun NewsDrawerHeader() {
    Text(
        text = "Crypto",
        style = MaterialTheme.typography.displaySmall,
        color = MaterialTheme.colorScheme.onSurfaceVariant,
        modifier = Modifier.padding(values)
    )
    Divider(thickness = Dp.Hairline, modifier = Modifier.fillMaxWidth(0.5f))
}

@Composable
fun NewsDrawerBody(
    menuPoint: List<DrawerMenuItem>,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues = values)
    ) {
        items(menuPoint) {
            DrawerElement(
                imageVector = it.imageVector,
                elementTitle = it.elementTitle,
                textStyle = it.textStyle,
                onItemClick = it.onItemClick
            )
        }
    }
}


@Composable
fun DrawerElement(
    imageVector: ImageVector,
    elementTitle: String,
    textStyle: TextStyle,
    onItemClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(40.dp)
            .padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onItemClick) {
            Icon(
                imageVector = imageVector,
                contentDescription = elementTitle,
                tint = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Text(
            text = elementTitle,
            style = textStyle,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


data class DrawerMenuItem(
    val imageVector: ImageVector,
    val elementTitle: String,
    val textStyle: TextStyle,
    val onItemClick: () -> Unit
)