package saxbybrands.housewares.saxbyhomerelay

// [FIREBASE|APPSFLYER][import_Intent]
// [FIREBASE][import_URI]
// [FIREBASE][imports_workmanager_settings]
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import saxbybrands.housewares.saxbyhomerelay.ui.composable.approot.AppRoot
import saxbybrands.housewares.saxbyhomerelay.ui.theme.ProductAppRQDMVTheme

// [FIREBASE][import_VisitRequestWorker]

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent { ProductAppRQDMVTheme { AppRoot() } }

        // [FIREBASE][onCreate_handleNotificationIntent]
    }

    // [FIREBASE|APPSFLYER][onNewIntent]

    // [FIREBASE][handleNotificationIntent]

    // [FIREBASE][scheduleClickTracking]

    // [FIREBASE][openExternalBrowser]
}
