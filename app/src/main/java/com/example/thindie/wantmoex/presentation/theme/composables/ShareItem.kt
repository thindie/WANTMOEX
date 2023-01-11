package com.example.thindie.wantmoex.presentation.theme.composables

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.thindie.wantmoex.data.network.getImg
import com.example.thindie.wantmoex.domain.entities.Share
import com.example.thindie.wantmoex.presentation.theme.Shapes

@Composable
fun ShareItem(share: Share, onClick: () -> Unit) {

    Card(shape = Shapes.extraLarge,
        modifier = Modifier
            .padding(start = 1.dp, end = 1.dp, top = 1.dp, bottom = 1.dp)
            .fillMaxWidth()
            .height(85.dp)) {
        Row() {
            Column(modifier = Modifier.padding(start = 20.dp)) {

                Text(text = share.shortName,
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.colorScheme.primary)
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    tonalElevation= 60.dp,
                    modifier = Modifier
                        .width(120.dp)
                        .clip(shape = Shapes.extraLarge)
                ) {
                    Text(text = share.prevPrice, style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier
                    )
                }

                Text(
                    text = share.id, style = MaterialTheme.typography.displaySmall,
                     modifier = Modifier.clickable { onClick() }

                )
            }
            Spacer(modifier = Modifier.weight(0.3f))
            Image(
                painter = rememberAsyncImagePainter(getImg(share)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
               colorFilter = ColorFilter.lighting(MaterialTheme.colorScheme.secondaryContainer, Color.Transparent),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(CenterVertically)
                    .size(72.dp)
                    .clip(CircleShape)

            )
            // Spacer(modifier = Modifier.weight(0.1f))
        }

    }
}

@Composable
fun ShareThingLoading() {
    Card(
        shape = Shapes.extraLarge, modifier = Modifier
            .padding(start = 5.dp, end = 5.dp, top = Dp.Hairline, bottom = Dp.Hairline)
            .fillMaxWidth()
            .height(80.dp)
    ) {

    }
}

@Composable
fun ShareItemExpanded(list: List<Share>, onClick: () -> Unit) {

    Card(shape = Shapes.extraLarge,
        modifier = Modifier
            .padding(start = 1.dp, end = 1.dp, top = 1.dp, bottom = 1.dp)
            .fillMaxWidth()
            .height(380.dp)) {
        Row() {
            Column(modifier = Modifier.padding(start = 20.dp)) {



                Text(
                    text = list[0].id, style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.clickable { onClick() }

                )
            }
            Spacer(modifier = Modifier.weight(0.3f))
            /*Image(
                painter = rememberAsyncImagePainter(getImg(share)),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                colorFilter = ColorFilter.lighting(MaterialTheme.colorScheme.secondaryContainer, Color.Transparent),
                modifier = Modifier
                    .padding(end = 20.dp)
                    .align(CenterVertically)
                    .size(72.dp)
                    .clip(CircleShape)

            )*/
            // Spacer(modifier = Modifier.weight(0.1f))
        }

    }
}