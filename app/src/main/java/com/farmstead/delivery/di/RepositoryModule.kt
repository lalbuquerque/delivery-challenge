package com.farmstead.delivery.di

import com.farmstead.delivery.repository.BaseRepository
import com.farmstead.delivery.domain.Delivery
import com.farmstead.delivery.repository.DeliveryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Singleton
    @Binds
    internal abstract fun bindDeliveryRepository(deliveryRepository: DeliveryRepository): BaseRepository<MutableList<Delivery>>
}