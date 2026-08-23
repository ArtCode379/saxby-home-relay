package saxbybrands.housewares.saxbyhomerelay.ui.composable.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FilterChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import org.koin.androidx.compose.koinViewModel
import saxbybrands.housewares.saxbyhomerelay.data.model.Product
import saxbybrands.housewares.saxbyhomerelay.data.model.ProductCategory
import saxbybrands.housewares.saxbyhomerelay.ui.composable.shared.RQDMVContentWrapper
import saxbybrands.housewares.saxbyhomerelay.ui.state.DataUiState
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.ProductViewModel

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: ProductViewModel = koinViewModel(),
    onNavigateToProductDetails: (productId: Int) -> Unit,
) {
    val state by viewModel.productsState.collectAsState()
    RQDMVContentWrapper(
        dataState = state,
        dataPopulated = {
            HomeProducts(
                (state as DataUiState.Populated).data,
                modifier,
                onNavigateToProductDetails,
            )
        },
        dataEmpty = {
            Text("Our collection is being refreshed.", modifier = Modifier.padding(24.dp))
        },
    )
}

@Composable
private fun HomeProducts(products: List<Product>, modifier: Modifier, onProduct: (Int) -> Unit) {
    var category by remember { mutableStateOf<ProductCategory?>(null) }
    val filtered = if (category == null) products else products.filter { it.category == category }
    Column(modifier.fillMaxSize().padding(horizontal = 16.dp)) {
        Text(
            "Good things for home",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(top = 18.dp),
        )
        Text(
            "Useful, beautiful and ready to collect",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.padding(vertical = 16.dp),
        ) {
            items(products.take(4)) { product ->
                Card(
                    Modifier.width(280.dp).height(180.dp).clickable { onProduct(product.id) },
                    shape = RoundedCornerShape(18.dp),
                ) {
                    AsyncImage(
                        product.imageUrl,
                        product.title,
                        Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                    )
                }
            }
        }
        LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            item {
                FilterChip(
                    selected = category == null,
                    onClick = { category = null },
                    label = { Text("All") },
                )
            }
            items(ProductCategory.entries) { item ->
                FilterChip(
                    selected = category == item,
                    onClick = { category = item },
                    label = { Text(stringResource(item.titleRes)) },
                )
            }
        }
        Text(
            "Shop the collection",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(vertical = 12.dp),
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            items(filtered, key = { it.id }) { product -> ProductCard(product, onProduct) }
        }
    }
}

@Composable
private fun ProductCard(product: Product, onProduct: (Int) -> Unit) {
    Card(
        onClick = { onProduct(product.id) },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        AsyncImage(
            product.imageUrl,
            product.title,
            Modifier.fillMaxWidth().height(132.dp),
            contentScale = ContentScale.Crop,
        )
        Column(Modifier.padding(12.dp)) {
            Text(product.title, style = MaterialTheme.typography.titleMedium)
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text(
                    "£%.2f".format(product.price),
                    color = MaterialTheme.colorScheme.primary,
                    style = MaterialTheme.typography.titleMedium,
                )
            }
            AssistChip(
                onClick = { onProduct(product.id) },
                label = { Text(stringResource(product.category.titleRes)) },
            )
        }
    }
}
