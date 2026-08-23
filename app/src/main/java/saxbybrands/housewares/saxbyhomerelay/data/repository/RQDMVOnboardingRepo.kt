package saxbybrands.housewares.saxbyhomerelay.data.repository

import saxbybrands.housewares.saxbyhomerelay.data.datastore.RQDMVOnboardingPrefs
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RQDMVOnboardingRepo(
    private val rqdmvOnboardingStoreManager: RQDMVOnboardingPrefs,
    private val coroutineDispatcher: CoroutineDispatcher,
) {

    fun observeOnboardingState(): Flow<Boolean?> {
        return rqdmvOnboardingStoreManager.onboardedStateFlow
    }

    suspend fun setOnboardingState(state: Boolean) {
        withContext(coroutineDispatcher) {
            rqdmvOnboardingStoreManager.setOnboardedState(state)
        }
    }
}