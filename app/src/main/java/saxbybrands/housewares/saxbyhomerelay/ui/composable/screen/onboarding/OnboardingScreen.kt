package saxbybrands.housewares.saxbyhomerelay.ui.composable.screen.onboarding

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lightbulb
import androidx.compose.material.icons.filled.Storefront
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import org.koin.androidx.compose.koinViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.RQDMVOnboardingVM

private data class Page(val title: String, val body: String, val icon: ImageVector)

@Composable
fun OnboardingScreen(
    modifier: Modifier = Modifier,
    viewModel: RQDMVOnboardingVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
) {
    val complete by viewModel.onboardingSetState.collectAsState()
    val pages =
        listOf(
            Page(
                "Make home feel effortless",
                "Discover practical appliances and considered accessories selected for real homes.",
                Icons.Default.Home,
            ),
            Page(
                "Choose with confidence",
                "Clear details and useful ideas help you find the right fit for every room.",
                Icons.Default.Lightbulb,
            ),
            Page(
                "Reserve, then collect",
                "Build your basket, reserve in seconds and collect from us within 24 hours.",
                Icons.Default.Storefront,
            ),
        )
    val pager = rememberPagerState { pages.size }
    LaunchedEffect(complete) { if (complete) onNavigateToHomeScreen() }
    Column(
        modifier.fillMaxSize().padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        HorizontalPager(state = pager, modifier = Modifier.weight(1f)) { index ->
            val page = pages[index]
            Column(Modifier.fillMaxSize(), Arrangement.Center, Alignment.CenterHorizontally) {
                Surface(
                    shape = RoundedCornerShape(24.dp),
                    color = MaterialTheme.colorScheme.surfaceVariant,
                ) {
                    Icon(
                        page.icon,
                        null,
                        Modifier.padding(36.dp).size(72.dp),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                }
                Spacer(Modifier.height(32.dp))
                Text(page.title, style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(12.dp))
                Text(
                    page.body,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            pages.indices.forEach { index ->
                Surface(
                    Modifier.size(if (index == pager.currentPage) 22.dp else 8.dp, 8.dp),
                    CircleShape,
                    color =
                        if (index == pager.currentPage) MaterialTheme.colorScheme.primary
                        else MaterialTheme.colorScheme.outline,
                ) {}
            }
        }
        Spacer(Modifier.height(24.dp))
        Button(
            onClick = {
                if (pager.currentPage == pages.lastIndex) viewModel.setOnboarded()
                else viewModel.setOnboarded()
            },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text(if (pager.currentPage == pages.lastIndex) "Get Started" else "Skip to shop")
        }
    }
}
