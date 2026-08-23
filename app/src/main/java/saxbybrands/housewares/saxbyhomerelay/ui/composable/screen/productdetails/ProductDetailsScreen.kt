package saxbybrands.housewares.saxbyhomerelay.ui.composable.screen.productdetails

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import kotlinx.coroutines.delay
import org.koin.compose.viewmodel.koinViewModel
import saxbybrands.housewares.saxbyhomerelay.data.model.Product
import saxbybrands.housewares.saxbyhomerelay.ui.composable.shared.RQDMVContentWrapper
import saxbybrands.housewares.saxbyhomerelay.ui.state.DataUiState
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.ProductDetailsViewModel

@Composable
fun ProductDetailsScreen(
    productId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.productDetailsState.collectAsState()
    LaunchedEffect(productId) { viewModel.observeProductDetails(productId) }
    RQDMVContentWrapper(
        dataState = state,
        dataPopulated = {
            ProductDetail(
                (state as DataUiState.Populated).data,
                modifier,
                viewModel::addProductToCart,
            )
        },
        dataEmpty = { Text("Product not found", Modifier.padding(24.dp)) },
    )
}

@Composable
private fun ProductDetail(product: Product, modifier: Modifier, add: () -> Unit) {
    var cartAdded by remember { mutableStateOf(false) }
    LaunchedEffect(cartAdded) {
        if (cartAdded) {
            delay(2000)
            cartAdded = false
        }
    }
    Box(modifier.fillMaxSize()) {
        Column(Modifier.verticalScroll(rememberScrollState())) {
            AsyncImage(
                product.imageUrl,
                product.title,
                Modifier.fillMaxWidth().height(300.dp),
                contentScale = ContentScale.Crop,
            )
            Column(Modifier.padding(20.dp)) {
                Text(product.title, style = MaterialTheme.typography.titleLarge)
                Text(
                    "£%.2f".format(product.price),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.primary,
                )
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                    modifier = Modifier.padding(vertical = 12.dp),
                ) {
                    Text(
                        stringResource(product.category.titleRes),
                        Modifier.padding(horizontal = 14.dp, vertical = 7.dp),
                    )
                }
                Text(
                    product.description,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
                Button(
                    onClick = {
                        add()
                        cartAdded = true
                    },
                    Modifier.fillMaxWidth().padding(top = 28.dp),
                ) {
                    Text("Add to Cart")
                }
            }
        }
        AnimatedVisibility(
            cartAdded,
            Modifier.align(Alignment.BottomCenter),
            enter = slideInVertically { it },
            exit = fadeOut(),
        ) {
            Surface(color = MaterialTheme.colorScheme.primary, modifier = Modifier.fillMaxWidth()) {
                Text(
                    "✓ Added to cart",
                    color = MaterialTheme.colorScheme.onPrimary,
                    modifier = Modifier.padding(16.dp),
                )
            }
        }
    }
}
