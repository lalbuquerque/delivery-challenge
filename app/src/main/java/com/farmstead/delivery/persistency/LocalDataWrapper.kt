package com.farmstead.delivery.persistency

import kotlinx.coroutines.flow.Flow

interface LocalDataWrapper <T> {
        suspend fun get(id: String?): T?
        suspend fun save(t: T)
        suspend fun getAsFlow(id: String?): Flow<T>?
}