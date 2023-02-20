package com.example.thindie.wantmoex.presentation.composables

import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.DrawerState
import androidx.compose.material.ModalDrawer
import androidx.compose.material.RadioButton
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.thindie.wantmoex.R
import com.example.thindie.wantmoex.presentation.composables.util.Mini
import com.example.thindie.wantmoex.presentation.composables.util.surfaceColor
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


private const val BTC = "BTC"
private const val ETH = "ETH"
private const val XRP = "XRP"
private const val DOGE = "DOGE"
private const val SHIBA = "SHIBA"
val tagList = listOf<String>(BTC, ETH, DOGE, SHIBA, XRP)


@Composable
fun CryptoDrawer(
    drawerState: DrawerState,
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
    onSelectTags: (List<String>) -> Unit,
    onSelectedLimit: (Int) -> Unit,

    ) {
    ModalDrawer(
        drawerContent = {
            AppDrawer(
                closeDrawer = { coroutineScope.launch { drawerState.close() } },
                selectTags = onSelectTags,
                selectLimit = onSelectedLimit
            )
        },
    ) {}
}


@Composable
private fun AppDrawer(
    closeDrawer: () -> Unit,
    selectTags: (List<String>) -> Unit,
    selectLimit: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .surfaceColor()
    ) {
        var listState = remember {
            mutableStateListOf<String>(BTC, ETH, DOGE, SHIBA, XRP)
        }
        CryptoDrawerHeader()

        val doCheck: (String) -> Unit =
            { listState = listState.approveCheck(it) as SnapshotStateList<String> }

        TagChecker(onCheck = { doCheck(it) }, tag = R.string.BTC)
        TagChecker(onCheck = { doCheck(it) }, tag = R.string.ETH)
        TagChecker(onCheck = { doCheck(it) }, tag = R.string.DOGE)
        TagChecker(onCheck = { doCheck(it) }, tag = R.string.XRP)
        TagChecker(onCheck = { doCheck(it) }, tag = R.string.SHIBA)
        OutlinedButton(onClick = { selectTags(listState); closeDrawer() }) {
            stringResource(id = R.string.onDone).Mini()
        }
        Divider(thickness = Dp.Hairline)
    }


}

private fun <T, R : T> List<T>.approveCheck(r: R): List<T> {
    val l = this.toMutableList()
    if (!l.contains(r)) l.add(r) else l.remove(r)
    return l
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
            painter = painterResource(id = R.drawable.cryptoviews),
            contentDescription = null
        )
        Text(
            text = stringResource(id = R.string.app_name),
            color = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Composable
fun TagChecker(
    onCheck: (String) -> Unit,
    @StringRes tag: Int,
) {
    var selected by remember { mutableStateOf(true) }
    val tagString = stringResource(id = tag)
    Row() {
        Text(text = tagString, color = MaterialTheme.colorScheme.onSurface)
        RadioButton(selected = selected, onClick = { selected = !selected; onCheck(tagString) })
    }
}
