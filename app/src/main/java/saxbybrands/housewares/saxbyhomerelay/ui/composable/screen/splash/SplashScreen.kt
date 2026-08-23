package saxbybrands.housewares.saxbyhomerelay.ui.composable.screen.splash

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.delay
import org.koin.androidx.compose.koinViewModel
import saxbybrands.housewares.saxbyhomerelay.ui.theme.SaxbyTeal
import saxbybrands.housewares.saxbyhomerelay.ui.theme.SaxbyTerracotta
import saxbybrands.housewares.saxbyhomerelay.ui.viewmodel.RQDMVSplashVM

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    viewModel: RQDMVSplashVM = koinViewModel(),
    onNavigateToHomeScreen: () -> Unit,
    onNavigateToOnboarding: () -> Unit,
) {
    val onboarded by viewModel.onboardedState.collectAsStateWithLifecycle()
    var visible by remember { mutableStateOf(false) }
    val scale by animateFloatAsState(if (visible) 1f else 0.8f, tween(800), label = "logo")
    LaunchedEffect(Unit) {
        visible = true
        delay(1500)
        if (onboarded) onNavigateToHomeScreen() else onNavigateToOnboarding()
    }
    Column(
        modifier =
            modifier
                .fillMaxSize()
                .background(Brush.verticalGradient(listOf(SaxbyTerracotta, SaxbyTeal))),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(
            Icons.Default.Home,
            null,
            Modifier.size(112.dp).scale(scale).background(Color.White, RoundedCornerShape(28.dp)),
            tint = SaxbyTerracotta,
        )
        Text(
            "Saxby Home Relay",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
        )
        Text("Thoughtful essentials for everyday living", color = Color.White.copy(alpha = 0.85f))
    }
}
