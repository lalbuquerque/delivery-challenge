package com.farmstead.delivery.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.farmstead.delivery.viewmodel.BaseViewModel
import com.farmstead.delivery.ui.common.ErrorScreen
import com.farmstead.delivery.ui.common.ProgressScreen
import com.farmstead.delivery.util.ViewState
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import timber.log.Timber

@Composable
fun <T> Content(
    viewModel: BaseViewModel<T>,
    SuccessScreen: @Composable (t: T) -> Unit
) {
    when (val viewState = viewModel.stateFlow.collectAsState().value) {

        is ViewState.Loading -> {
            Timber.i("Content: LOADING")
            ProgressScreen()
        }
        is ViewState.Success -> {
            Timber.i("Content: SUCCESS")
            val isRefreshing by viewModel.isRefreshing.collectAsState()
            SwipeRefresh(
                state = rememberSwipeRefreshState(isRefreshing = isRefreshing),
                onRefresh = { viewModel.refresh() }
            ) {
                viewState.data?.let { SuccessScreen(t = it) }
            }
        }
        is ViewState.Error -> {
            Timber.i("Content: ERROR")
            ErrorScreen(message = viewState.message, refresh = viewModel::refresh)
        }
    }
}