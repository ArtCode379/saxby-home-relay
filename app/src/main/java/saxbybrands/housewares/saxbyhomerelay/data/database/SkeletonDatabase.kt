package saxbybrands.housewares.saxbyhomerelay.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import saxbybrands.housewares.saxbyhomerelay.data.dao.CartItemDao
import saxbybrands.housewares.saxbyhomerelay.data.dao.OrderDao
import saxbybrands.housewares.saxbyhomerelay.data.database.converter.Converters
import saxbybrands.housewares.saxbyhomerelay.data.entity.CartItemEntity
import saxbybrands.housewares.saxbyhomerelay.data.entity.OrderEntity

@Database(
    entities = [CartItemEntity::class, OrderEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class RQDMVDatabase : RoomDatabase() {

    abstract fun cartItemDao(): CartItemDao

    abstract fun orderDao(): OrderDao
}