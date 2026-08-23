package saxbybrands.housewares.saxbyhomerelay.di

import androidx.room.Room
import org.koin.dsl.module
import saxbybrands.housewares.saxbyhomerelay.data.database.RQDMVDatabase

private const val DB_NAME = "rqdmv_db"

val databaseModule = module {
    single {
        Room.databaseBuilder(context = get(), klass = RQDMVDatabase::class.java, name = DB_NAME)
            .build()
    }

    single { get<RQDMVDatabase>().cartItemDao() }

    single { get<RQDMVDatabase>().orderDao() }
}
