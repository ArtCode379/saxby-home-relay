package saxbybrands.housewares.saxbyhomerelay.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import saxbybrands.housewares.saxbyhomerelay.data.repository.RQDMVOnboardingRepo

class RQDMVSplashVM(private val onboardingRepository: RQDMVOnboardingRepo) : ViewModel() {
    val onboardedState: StateFlow<Boolean> =
        onboardingRepository
            .observeOnboardingState()
            .map { it == true }
            .stateIn(scope = viewModelScope, started = SharingStarted.Eagerly, initialValue = false)
}
