package saxbybrands.housewares.saxbyhomerelay.ui.composable.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import org.koin.compose.viewmodel.koinViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.composable.shared.RQDMVContentWrapper
import saxbybrands.housewares.saxbyhomerelay.ui.state.CartItemUiState
import saxbybrands.housewares.saxbyhomerelay.ui.state.DataUiState
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.CartViewModel

@Composable
fun CartScreen(
    modifier: Modifier = Modifier,
    viewModel: CartViewModel = koinViewModel(),
    onNavigateToCheckoutScreen: () -> Unit,
) {
    val state by viewModel.cartItemsState.collectAsStateWithLifecycle()
    val total by viewModel.totalPrice.collectAsStateWithLifecycle()
    RQDMVContentWrapper(
        dataState = state,
        dataPopulated = {
            CartList(
                (state as DataUiState.Populated).data,
                total,
                modifier,
                viewModel::incrementProductInCart,
                viewModel::decrementItemInCart,
                onNavigateToCheckoutScreen,
            )
        },
        dataEmpty = {
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text(
                    "Your basket is ready for good things",
                    style = MaterialTheme.typography.titleLarge,
                )
                Text("Start shopping to reserve your favourites.")
            }
        },
    )
}

@Composable
private fun CartList(
    items: List<CartItemUiState>,
    total: Double,
    modifier: Modifier,
    plus: (Int) -> Unit,
    minus: (Int) -> Unit,
    checkout: () -> Unit,
) {
    Column(modifier.fillMaxSize().padding(16.dp)) {
        LazyColumn(Modifier.weight(1f), verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(items, key = { it.productId }) { item ->
                Card(shape = RoundedCornerShape(16.dp)) {
                    Row(
                        Modifier.fillMaxWidth().padding(12.dp),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        AsyncImage(
                            item.productImageUrl,
                            item.productTitle,
                            Modifier.size(72.dp),
                            contentScale = ContentScale.Crop,
                        )
                        Column(Modifier.weight(1f).padding(horizontal = 12.dp)) {
                            Text(item.productTitle, style = MaterialTheme.typography.titleMedium)
                            Text(
                                "£%.2f".format(item.productPrice),
                                color = MaterialTheme.colorScheme.primary,
                            )
                        }
                        IconButton(onClick = { minus(item.productId) }) {
                            Icon(Icons.Default.Remove, "Decrease")
                        }
                        Text(item.quantity.toString())
                        IconButton(onClick = { plus(item.productId) }) {
                            Icon(Icons.Default.Add, "Increase")
                        }
                    }
                }
            }
        }
        Row(Modifier.fillMaxWidth().padding(vertical = 12.dp), Arrangement.SpaceBetween) {
            Text("Total", style = MaterialTheme.typography.titleLarge)
            Text(
                "£%.2f".format(total),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
        }
        Button(onClick = checkout, Modifier.fillMaxWidth().height(52.dp)) {
            Text("Proceed to Checkout")
        }
    }
}
