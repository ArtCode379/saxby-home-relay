package saxbybrands.housewares.saxbyhomerelay.di

import saxbybrands.housewares.saxbyhomerelay.data.repository.CartRepository
import saxbybrands.housewares.saxbyhomerelay.data.repository.RQDMVOnboardingRepo
import saxbybrands.housewares.saxbyhomerelay.data.repository.OrderRepository
import saxbybrands.housewares.saxbyhomerelay.data.repository.ProductRepository

import org.koin.core.qualifier.named
import org.koin.dsl.module

val dataModule = module {
    includes(databaseModule, dataStoreModule)

    single {
        RQDMVOnboardingRepo(
            rqdmvOnboardingStoreManager = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single { ProductRepository() }

    single {
        CartRepository(
            cartItemDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }

    single {
        OrderRepository(
            orderDao = get(),
            coroutineDispatcher = get(named("IO"))
        )
    }
}