package com.example.thindie.wantmoex.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.integerResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.R
import com.example.thindie.wantmoex.presentation.composables.util.*


val tagRList = listOf(
    R.string.BTC,
    R.string.ETH,
    R.string.DOGE,
    R.string.SHIBA,
    R.string.XRP
)

val limitList = listOf(
    R.integer.list_top_size, R.integer.list_medium_size, R.integer.start_list_size
)

@Composable
fun AppDrawer(
    onSelectTags: (List<String>) -> Unit,
    onSelectedLimit: (Int) -> Unit,
    closeDrawer: () -> Unit,

    ) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .surfaceColor()
            .eightStartPadding()
    ) {
        var listState = remember { mutableStateListOf(BTC, ETH, DOGE, SHIBA, XRP) }


        CryptoDrawerHeader()
        val doCheck: (String) -> Unit =
            { listState = listState.approveCheck(it).toMutableStateList() }

        LazyColumn(
            Modifier
                .fillMaxWidth()
                .surfaceColor()
        ) {
            items(tagRList) {
                TagChecker<String>(onCheck = { doCheck(it) }, tag = it)
            }
        }
        Spacer(modifier = Modifier.weight(0.2f, false))
        OutlinedButton(onClick = { onSelectTags(listState.toList()); closeDrawer() }) {
            stringResource(id = R.string.onDone).Mini()
        }
        Spacer(modifier = Modifier.weight(0.2f, false))
        Divider(thickness = Dp.Hairline)
        Spacer(modifier = Modifier.weight(0.2f, true))
        CryptoDrawerCellar(onSelectedLimit)
    }

}


@Composable
fun CryptoDrawerHeader(modifier: Modifier = Modifier) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .surfaceColor()
            .height(180.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cryptoviews), contentDescription = null
        )
        stringResource(id = R.string.app_name).HeadLine()
    }
}

@Composable
inline fun <reified T> TagChecker(
    modifier: Modifier = Modifier,
    crossinline onCheck: (T) -> Unit,
    @StringRes tag: Int,
) {

    var selected by remember { mutableStateOf(true) }
    val tagString = when (T::class) {
        String::class -> stringResource(id = tag)
        Int::class -> integerResource(id = tag)
        else -> {
            tag.toString()
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .eightStartPadding()
            .fillMaxWidth(0.7f)
    ) {
        tagString.toString().Medium()
        Spacer(modifier = modifier.weight(0.4f))
        RadioButton(
            selected = selected,
            onClick = { selected = !selected; onCheck(tagString as T) },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.surfaceTint,
                unselectedColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}

@Composable
fun CryptoDrawerCellar(onSetLimit: (Int) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .surfaceColor()
            .eightStartPadding()
    ) {
        Spacer(modifier = Modifier.weight(0.7f))
        LazyColumn(
            Modifier
                .fillMaxWidth()
                .surfaceColor()
        ) {
            items(limitList) {
                TagChecker<Int>(onCheck = { onSetLimit(it) }, tag = it)
            }
        }
    }
}
