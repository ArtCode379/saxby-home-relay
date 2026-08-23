package saxbybrands.housewares.saxbyhomerelay.di

import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import saxbybrands.housewares.saxbyhomerelay.data.datastore.RQDMVOnboardingPrefs

val dataStoreModule = module { single { RQDMVOnboardingPrefs(androidContext()) } }
