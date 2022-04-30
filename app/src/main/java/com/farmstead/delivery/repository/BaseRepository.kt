package com.farmstead.delivery.repository

import android.content.Context
import com.farmstead.delivery.R
import com.farmstead.delivery.util.ViewState
import com.farmstead.delivery.util.isNetworkAvailable
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

abstract class BaseRepository<T>(
    private val context: Context,
    private val ioDispatcher: CoroutineDispatcher
) {
    protected abstract suspend fun query(id: String?): T?

    protected abstract suspend fun fetch(url: String?): T

    protected abstract suspend fun saveFetchResult(t: T)

    fun getResult(id: String?, url: String?): Flow<ViewState<T?>> = flow {
        emit(ViewState.Loading)
        query(id)?.let {
            emit(ViewState.Success(it))
            refresh(url)
            emit(ViewState.Success(query(id)))
        } ?: run {
            if (context.isNetworkAvailable()) {
                try {
                    refresh(url)
                    emit(ViewState.Success(query(id)))
                } catch (t: Throwable) {
                    emit(ViewState.Error(context.getString(R.string.failed_loading_msg)))
                }
            } else {
                emit(ViewState.Error(context.getString(R.string.failed_network_msg)))
            }
        }
    }.flowOn(ioDispatcher)
        .catch { Timber.e(it) }

    private suspend fun refresh(url: String?) {
        saveFetchResult(fetch(url))
    }
}