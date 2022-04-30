package com.farmstead.delivery.di

import android.content.Context
import androidx.room.Room
import com.farmstead.delivery.R
import com.farmstead.delivery.persistency.datastore.DataStoreLocalDataWrapper
import com.farmstead.delivery.persistency.LocalDataWrapper
import com.farmstead.delivery.persistency.room.AppDatabase
import com.farmstead.delivery.domain.CurrentDelivery
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            context.getString(R.string.database)
        ).build()
    }

    @Singleton
    @Provides
    fun provideDeliveryDao(db: AppDatabase) = db.deliveryDao()

    @Singleton
    @Provides
    fun provideDataStoreLocalDataWrapper(wrapper: DataStoreLocalDataWrapper): LocalDataWrapper<CurrentDelivery> {
        return wrapper
    }
}