package com.farmstead.delivery.viewmodel

import com.farmstead.delivery.repository.BaseRepository
import com.farmstead.delivery.domain.Delivery
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DeliveryViewModel @Inject constructor(
    repository: BaseRepository<MutableList<Delivery>>
) : BaseViewModel<MutableList<Delivery>>(repository)