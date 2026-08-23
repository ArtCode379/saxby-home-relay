package saxbybrands.housewares.saxbyhomerelay.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import saxbybrands.housewares.saxbyhomerelay.data.entity.OrderEntity

@Dao
interface OrderDao {

    @Query("SELECT * FROM orders") fun observeAll(): Flow<List<OrderEntity>>

    @Query("SELECT * FROM orders") suspend fun getAll(): List<OrderEntity>

    @Query("SELECT * FROM orders WHERE order_number = :orderNumber")
    fun observeByNumber(orderNumber: String): Flow<OrderEntity?>

    @Query("SELECT * FROM orders WHERE order_number = :orderNumber")
    suspend fun getByNumber(orderNumber: String): OrderEntity?

    @Insert suspend fun save(orderEntity: OrderEntity): Long

    @Query("DELETE FROM orders WHERE order_number = :orderNumber")
    suspend fun deleteByNumber(orderNumber: String)
}
