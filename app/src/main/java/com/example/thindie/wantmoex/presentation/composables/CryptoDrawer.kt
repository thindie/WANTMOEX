package com.example.thindie.wantmoex.presentation.composables

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.RadioButton
import androidx.compose.material.RadioButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.ShapeDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
val list = listOf(
    R.integer.start_list_size to true,
    R.integer.list_medium_size to true,
    R.integer.list_top_size to true
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

        val listState = remember { mutableStateListOf(BTC, ETH, DOGE, SHIBA, XRP) }
        val onApplyTags: () -> Unit = { onSelectTags(listState.toList()); closeDrawer() }
        val onApplyLimits: (Int) -> Unit = { onSelectedLimit(it); closeDrawer() }



        CryptoDrawerHeader()
        CryptoDrawerBody(
            onCheck = { if (!listState.contains(it)) listState.add(it) },
            onUncheck = { listState.remove(it) }) {
            onApplyTags()
        }
        Divider(thickness = Dp.Hairline)
        CryptoDrawerCellar(onApplyLimits)
    }
}


@Composable
fun CryptoDrawerHeader(modifier: Modifier = Modifier) {
    val color = MaterialTheme.colorScheme.surfaceTint.copy(0.1f)
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxWidth()
            .background(color = color)
            .height(180.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.cryptoviews),
            contentDescription = null,
            modifier = Modifier.clip(CircleShape),
        )
        stringResource(id = R.string.app_name).HeadLine()
    }
}

@Composable
fun CryptoDrawerBody(
    onCheck: @Composable (String) -> Unit,
    onUncheck: @Composable (String) -> Unit,
    onApplyTags: () -> Unit,
) {
    LazyColumn(
        Modifier
            .fillMaxWidth()
            .surfaceColor()
            .eightStartPadding()
    ) {
        items(tagRList) { tag ->
            TagChecker<String>(
                onCheck = { onCheck(stringResource(id = tag)) },
                onUncheck = { onUncheck(stringResource(id = tag)) },
                tag = tag
            )
        }
    }

    OutlinedButton(onClick = { onApplyTags() }) {
        stringResource(id = R.string.apply_news_tags).Mini()
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
        Spacer(modifier = Modifier.padding(top = 20.dp))
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            stringResource(id = R.string.apply_limits_tags).HeadLine()
        }

        CheckerList(list = list,
            checked = {
                onSetLimit(it)
            })
    }
}


@Composable
fun CheckerList(list: List<Pair<Int, Boolean>>, checked: (Int) -> Unit) {
    LazyColumn() {
        items(list) { pair ->
            TagChecker<Int>(
                onCheck = { val limit = integerResource(id = pair.first); checked(limit); Log.d("SERVICE_TAG", limit.toString()) },
                onUncheck = { },
                tag = pair.first,
                isSelected = pair.second
            )
        }
    }
}


@Composable
inline fun <reified T> TagChecker(
    modifier: Modifier = Modifier,
    crossinline onCheck: @Composable (T) -> Unit,
    crossinline onUncheck: @Composable (T) -> Unit,
    isSelected: Boolean = true,
    tag: Int,
) {

    var isSelectedValue by remember { mutableStateOf(isSelected) }
    val tagString = when (T::class) {
        String::class -> stringResource(id = tag)
        Int::class -> integerResource(id = tag)
        else -> {
            tag.toString()
        }
    }

    if (isSelectedValue) {
        onCheck(tagString as T)
    } else {
        onUncheck(tagString as T);
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .eightStartPadding()
            .background(MaterialTheme.colorScheme.onSurfaceVariant.copy(0.01f))
            .clip(ShapeDefaults.ExtraLarge)
            .fillMaxWidth(0.9f)
    ) {
        tagString.toString().Body()
        Spacer(modifier = modifier.weight(0.4f))
        RadioButton(
            selected = when (T::class) {
                Int::class -> {
                    isSelectedValue = true; false
                }; else -> isSelectedValue
            },
            onClick = { isSelectedValue = !isSelectedValue; },
            colors = RadioButtonDefaults.colors(
                selectedColor = MaterialTheme.colorScheme.surfaceTint,
                unselectedColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }
}
