package com.farmstead.delivery

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.farmstead.delivery.persistency.LocalDataWrapper
import com.farmstead.delivery.persistency.room.delivery.DeliveryDao
import com.farmstead.delivery.domain.CurrentDelivery
import com.farmstead.delivery.domain.Deliveries
import com.farmstead.delivery.domain.Delivery
import com.farmstead.delivery.network.DeliveryService
import com.farmstead.delivery.repository.DeliveryRepository
import com.farmstead.delivery.util.ViewState
import com.farmstead.delivery.util.isNetworkAvailable
import com.farmstead.delivery.viewmodel.DeliveryViewModel
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.hamcrest.CoreMatchers.`is`
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DeliveryViewModelTest {

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var api: DeliveryService

    @Mock
    private lateinit var dao: DeliveryDao

    @Mock
    private lateinit var context: Context

    @Mock
    private lateinit var localDataWrapper: LocalDataWrapper<CurrentDelivery>

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
    }

    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        mockkStatic("com.farmstead.delivery.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true

        testCoroutineRule.runBlockingTest {
            val flow = flow {
                emit(CurrentDelivery(-1L, ""))
            }

            `when`(dao.getDeliveries()).thenReturn(null)
            `when`(api.getUpcomingDeliveries(anyString(), anyString())).thenReturn(Deliveries(generateDeliveryList()))
            `when`(localDataWrapper.getAsFlow("")).thenReturn(flow)
        }

        val repository = DeliveryRepository(dao, api, context, Dispatchers.Main, localDataWrapper)
        testCoroutineRule.pauseDispatcher()
        val viewModel = DeliveryViewModel(repository)
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Loading))

        testCoroutineRule.resumeDispatcher()
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Success(null)))
    }

    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        mockkStatic("com.farmstead.delivery.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns true
        testCoroutineRule.runBlockingTest {
            `when`(api.getUpcomingDeliveries("", "")).thenThrow(RuntimeException(""))
        }
        val repository = DeliveryRepository(dao, api, context, Dispatchers.Main, localDataWrapper)
        testCoroutineRule.pauseDispatcher()
        val viewModel = DeliveryViewModel(repository)
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Loading))

        testCoroutineRule.resumeDispatcher()
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Error(errorMsg)))
    }

    @Test
    fun givenNetworkUnAvailable_whenFetch_shouldReturnError() {
        val errorMsg = "error message"
        `when`(context.getString(anyInt())).thenReturn(errorMsg)
        mockkStatic("com.farmstead.delivery.util.ContextExtKt")
        every {
            context.isNetworkAvailable()
        } returns false
        val repository = DeliveryRepository(dao, api, context, Dispatchers.Main, localDataWrapper)
        testCoroutineRule.pauseDispatcher()
        val viewModel = DeliveryViewModel(repository)
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Loading))

        testCoroutineRule.resumeDispatcher()
        assertThat(viewModel.stateFlow.value, `is`(ViewState.Error(errorMsg)))
    }

    private fun generateDeliveryList() =
        listOf(
            Delivery(
                id = 4L,
                deliveryDayAbbreviated = "day4",
                deliveryDate = "date4",
                deliveryTimeRange = "timerange4",
                scheduledStart = "",
                scheduledEnd = "",
                deliveryType = "",
                deliveryState = "",
                orderItemsCount = 0,
                orderItemImageUrls = emptyList(),
                orderItems = emptyList()),

            Delivery(
                id = 8L,
                deliveryDayAbbreviated = "day8",
                deliveryDate = "date8",
                deliveryTimeRange = "timerange8",
                scheduledStart = "",
                scheduledEnd = "",
                deliveryType = "",
                deliveryState = "",
                orderItemsCount = 0,
                orderItemImageUrls = emptyList(),
                orderItems = emptyList()),
        )
}