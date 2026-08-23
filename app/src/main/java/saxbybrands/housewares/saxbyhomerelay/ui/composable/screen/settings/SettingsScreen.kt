package saxbybrands.housewares.saxbyhomerelay.ui.composable.screen.settings

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Business
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Verified
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier.fillMaxSize().padding(20.dp)) {
        Text("About", style = MaterialTheme.typography.headlineMedium)
        Card(Modifier.fillMaxWidth().padding(vertical = 16.dp), shape = RoundedCornerShape(16.dp)) {
            SettingLine("Company", "SAXBY BRANDS LTD", { Icon(Icons.Default.Business, null) })
            HorizontalDivider()
            SettingLine("App version", "1.0", { Icon(Icons.Default.Verified, null) })
        }
        Text("Help & legal", style = MaterialTheme.typography.titleLarge)
        Text(
            "Questions about a reservation or product? Our support site has the latest company information.",
            modifier = Modifier.padding(vertical = 12.dp),
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
        Button(
            onClick = {
                context.startActivity(
                    Intent(Intent.ACTION_VIEW, Uri.parse("https://saxbybrands.casa"))
                )
            },
            Modifier.fillMaxWidth(),
        ) {
            Icon(Icons.Default.Language, null)
            Text("  Customer Support")
        }
    }
}

@Composable
private fun SettingLine(label: String, value: String, icon: @Composable () -> Unit) {
    androidx.compose.foundation.layout.Row(Modifier.fillMaxWidth().padding(16.dp)) {
        icon()
        Column(Modifier.padding(start = 12.dp)) {
            Text(label, style = MaterialTheme.typography.labelLarge)
            Text(value, color = MaterialTheme.colorScheme.onSurfaceVariant)
        }
    }
}
