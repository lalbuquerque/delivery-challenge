package com.farmstead.delivery.persistency.datastore

import com.farmstead.delivery.domain.CurrentDelivery
import com.farmstead.delivery.persistency.LocalDataWrapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataStoreLocalDataWrapper @Inject constructor(private val dataStoreManager: DataStoreManager) :
    LocalDataWrapper<CurrentDelivery> {

    override suspend fun get(id: String?): CurrentDelivery? {
        return dataStoreManager.getCurrentDelivery().take(1).first()
    }

    override suspend fun save(t: CurrentDelivery) {
        dataStoreManager.saveCurrentDeliveryInfoToDataStore(t)
    }

    override suspend fun getAsFlow(id: String?): Flow<CurrentDelivery> =
        dataStoreManager.getCurrentDelivery()

}