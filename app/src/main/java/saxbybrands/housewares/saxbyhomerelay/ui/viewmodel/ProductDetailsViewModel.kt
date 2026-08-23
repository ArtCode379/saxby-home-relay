package saxbybrands.housewares.saxbyhomerelay.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import saxbybrands.housewares.saxbyhomerelay.data.model.Product
import saxbybrands.housewares.saxbyhomerelay.data.repository.CartRepository
import saxbybrands.housewares.saxbyhomerelay.data.repository.ProductRepository
import saxbybrands.housewares.saxbyhomerelay.ui.state.DataUiState

class ProductDetailsViewModel(
    private val productRepository: ProductRepository,
    private val cartRepository: CartRepository,
) : ViewModel() {
    private val _productDetailState = MutableStateFlow<DataUiState<Product>>(DataUiState.Initial)
    val productDetailsState: StateFlow<DataUiState<Product>>
        get() = _productDetailState.asStateFlow()

    fun observeProductDetails(productId: Int) {
        viewModelScope.launch {
            productRepository.observeById(productId).collect { product ->
                _productDetailState.update { DataUiState.from(product) }
            }
        }
    }

    fun addProductToCart() {
        viewModelScope.launch {
            val state = _productDetailState.value
            if (state is DataUiState.Populated) {
                cartRepository.incrementProductQuantityOrAdd(state.data)
            }
        }
    }
}
