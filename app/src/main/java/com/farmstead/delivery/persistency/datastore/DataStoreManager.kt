package com.farmstead.delivery.persistency.datastore;

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.longPreferencesKey
import com.farmstead.delivery.domain.CurrentDelivery
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreManager @Inject constructor(val context: Context) {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DELIVERY_DATASTORE)

    companion object {
        const val DELIVERY_DATASTORE = "DELIVERY_DATASTORE";
        val CURRENT_DELIVERY_ID = longPreferencesKey("CURRENT_DELIVERY_ID")
        val CURRENT_DELIVERY_LABEL = stringPreferencesKey("CURRENT_DELIVERY_LABEL")
    }

    suspend fun saveCurrentDeliveryInfoToDataStore(currentDelivery: CurrentDelivery) {
        context.dataStore.edit {
            it[CURRENT_DELIVERY_ID] = currentDelivery.id
            it[CURRENT_DELIVERY_LABEL] = currentDelivery.label
        }
    }

    fun getCurrentDelivery() = context.dataStore.data.map {
        CurrentDelivery(
            id = it[CURRENT_DELIVERY_ID] ?: -1L,
            label = it[CURRENT_DELIVERY_LABEL] ?: "",
        )
    }
}


