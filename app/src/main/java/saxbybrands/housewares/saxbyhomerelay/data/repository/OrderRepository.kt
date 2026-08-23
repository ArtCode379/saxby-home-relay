package saxbybrands.housewares.saxbyhomerelay.data.repository

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import saxbybrands.housewares.saxbyhomerelay.data.dao.OrderDao
import saxbybrands.housewares.saxbyhomerelay.data.entity.OrderEntity

class OrderRepository(
    private val orderDao: OrderDao,
    private val coroutineDispatcher: CoroutineDispatcher,
) {
    suspend fun save(orderEntity: OrderEntity): Long {
        return withContext(coroutineDispatcher) { orderDao.save(orderEntity) }
    }

    fun observeAll(): Flow<List<OrderEntity>> {
        return orderDao.observeAll()
    }

    suspend fun deleteByNumber(orderNumber: String) {
        withContext(coroutineDispatcher) { orderDao.deleteByNumber(orderNumber) }
    }
}
