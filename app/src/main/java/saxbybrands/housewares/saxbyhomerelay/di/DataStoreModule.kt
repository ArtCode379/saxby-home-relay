package saxbybrands.housewares.saxbyhomerelay.di

import saxbybrands.housewares.saxbyhomerelay.data.datastore.RQDMVOnboardingPrefs
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataStoreModule = module {
    single { RQDMVOnboardingPrefs(androidContext()) }
}