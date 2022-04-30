package com.farmstead.delivery.repository

import android.content.Context
import com.farmstead.delivery.persistency.room.delivery.DeliveryDao
import com.farmstead.delivery.persistency.room.delivery.asDomainModel
import com.farmstead.delivery.persistency.LocalDataWrapper
import com.farmstead.delivery.di.IoDispatcher
import com.farmstead.delivery.domain.CurrentDelivery
import com.farmstead.delivery.domain.Delivery
import com.farmstead.delivery.domain.asDatabaseModel
import com.farmstead.delivery.domain.toCurrentDelivery
import com.farmstead.delivery.network.DeliveryService
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.take
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeliveryRepository @Inject constructor(
    private val dao: DeliveryDao,
    private val api: DeliveryService,
    context: Context,
    @IoDispatcher ioDispatcher: CoroutineDispatcher,
    private val localDataWrapper: LocalDataWrapper<CurrentDelivery>
) : BaseRepository<MutableList<Delivery>>(context, ioDispatcher) {

    override suspend fun query(id: String?): MutableList<Delivery>? {
        val deliveries = dao.getDeliveries()?.asDomainModel()
        saveCurrentDeliveryOnLocalStoreIfNeeded(deliveries)
        return deliveries
    }

    override suspend fun fetch(url: String?): MutableList<Delivery> =
        api.getUpcomingDeliveries("2", "jSsxdbg89YWiAQFXFmLL").deliveries.toMutableList()

    override suspend fun saveFetchResult(t: MutableList<Delivery>) {
        dao.insert(t.asDatabaseModel())
    }

    private suspend fun saveCurrentDeliveryOnLocalStoreIfNeeded(deliveries: MutableList<Delivery>?) {
        localDataWrapper.getAsFlow("")?.take(1)?.collect { delivery ->
            if (delivery.id == -1L && deliveries?.isNotEmpty() == true) {
                deliveries[0].let {
                    localDataWrapper.save(it.toCurrentDelivery())
                }
            }
        }
    }
}