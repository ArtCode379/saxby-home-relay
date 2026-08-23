package saxbybrands.housewares.saxbyhomerelay.ui.composable.screen.checkout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import org.koin.androidx.compose.koinViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.state.DataUiState
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.CheckoutViewModel

@Composable
fun CheckoutScreen(
    modifier: Modifier = Modifier,
    viewModel: CheckoutViewModel = koinViewModel(),
    onNavigateToOrdersScreen: () -> Unit,
) {
    val order by viewModel.orderState.collectAsStateWithLifecycle()
    val invalid by viewModel.emailInvalidState.collectAsStateWithLifecycle()
    if (order is DataUiState.Populated) {
        CheckoutDialog(onConfirm = onNavigateToOrdersScreen)
    }
    Column(modifier.fillMaxSize().verticalScroll(rememberScrollState()).padding(20.dp)) {
        Text("Reserve your order", style = MaterialTheme.typography.headlineMedium)
        Text(
            "Enter collection details. We will hold your order in store for 24 hours.",
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Spacer(Modifier.height(20.dp))
        OutlinedTextField(
            viewModel.customerFirstName,
            viewModel::updateCustomerFirstName,
            Modifier.fillMaxWidth(),
            label = { Text("First name") },
            singleLine = true,
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            viewModel.customerLastName,
            viewModel::updateCustomerLastName,
            Modifier.fillMaxWidth(),
            label = { Text("Last name") },
            singleLine = true,
        )
        Spacer(Modifier.height(12.dp))
        OutlinedTextField(
            viewModel.customerEmail,
            viewModel::updateCustomerEmail,
            Modifier.fillMaxWidth(),
            label = { Text("Email or phone") },
            isError = invalid,
            supportingText = { if (invalid) Text("Enter a valid email address") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            singleLine = true,
        )
        Spacer(Modifier.height(24.dp))
        Text("Collection promise", style = MaterialTheme.typography.titleMedium)
        Text("Your confirmed reservation will be waiting at the store for the next 24 hours.")
        Spacer(Modifier.height(24.dp))
        val ready =
            viewModel.customerFirstName.isNotBlank() &&
                viewModel.customerLastName.isNotBlank() &&
                viewModel.customerEmail.isNotBlank()
        Button(
            onClick = viewModel::placeOrder,
            enabled = ready,
            modifier = Modifier.fillMaxWidth().height(52.dp),
        ) {
            Text("Place Reservation")
        }
    }
}
