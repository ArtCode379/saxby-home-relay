package saxbybrands.housewares.saxbyhomerelay.ui.composable.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import saxbybrands.housewares.saxbyhomerelay.data.entity.OrderEntity
import saxbybrands.housewares.saxbyhomerelay.ui.composable.shared.RQDMVContentWrapper
import saxbybrands.housewares.saxbyhomerelay.ui.state.DataUiState
import saxbybrands.housewares.saxbyhomerelay.ui.theme.SaxbySuccess
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.OrderViewModel

@Composable
fun OrdersScreen(modifier: Modifier = Modifier, viewModel: OrderViewModel = koinViewModel()) {
    val state by viewModel.ordersState.collectAsState()
    RQDMVContentWrapper(
        dataState = state,
        dataPopulated = {
            LazyColumn(
                modifier.fillMaxSize().padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
            ) {
                items((state as DataUiState.Populated).data.sortedByDescending { it.timestamp }) {
                    OrderCard(it)
                }
            }
        },
        dataEmpty = {
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Text("No orders yet", style = MaterialTheme.typography.titleLarge)
                Text("Your reservations will appear here.")
            }
        },
    )
}

@Composable
private fun OrderCard(order: OrderEntity) {
    Card(shape = RoundedCornerShape(16.dp)) {
        Column(Modifier.padding(16.dp)) {
            Row(Modifier.fillMaxWidth(), Arrangement.SpaceBetween) {
                Text("Order #${order.orderNumber}", style = MaterialTheme.typography.titleMedium)
                Surface(
                    shape = RoundedCornerShape(20.dp),
                    color = SaxbySuccess.copy(alpha = 0.12f),
                ) {
                    Text(
                        "Reserved",
                        color = SaxbySuccess,
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp),
                    )
                }
            }
            Text(
                order.timestamp.toLocalDate().toString(),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Text(order.description, modifier = Modifier.padding(vertical = 10.dp))
            Text(
                "£%.2f".format(order.price),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )
            Text("Collection held for 24 hours", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
