package br.com.alaksion.core_ui.components

import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filter

@Composable
fun LazyListPager(
    onNext: () -> Unit,
    shouldLoadNext: Boolean,
    isLoading: Boolean,
    state: LazyListState
) {
    val callBack by rememberUpdatedState(newValue = onNext)

    val loadNext = remember {
        derivedStateOf {
            if (isLoading || shouldLoadNext.not()) {
                false
            } else {
                val layoutInfo = state.layoutInfo
                val totalItems = layoutInfo.totalItemsCount
                val lastVisibleIndex = layoutInfo.visibleItemsInfo.lastOrNull()?.index ?: 0

                lastVisibleIndex + 1 >= totalItems
            }
        }
    }

    LaunchedEffect(key1 = loadNext, key2 = callBack) {
        snapshotFlow { loadNext.value }
            .filter { it }
            .collect { callBack() }
    }

}