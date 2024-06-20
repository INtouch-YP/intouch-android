package care.intouch.app

 import android.os.Bundle
 import androidx.activity.ComponentActivity
 import androidx.activity.compose.setContent
 import androidx.compose.foundation.layout.Column
 import androidx.compose.foundation.layout.fillMaxSize
 import androidx.compose.material3.Surface
 import androidx.compose.ui.Modifier
 import androidx.navigation.compose.rememberNavController
 import care.intouch.app.core.navigation.AppNavScreen
 import care.intouch.app.core.navigation.Authentication
 import care.intouch.app.core.navigation.AuthorizationRouteBranch
 import care.intouch.app.core.navigation.navhost.MainNavHost
 import care.intouch.uikit.theme.InTouchTheme
 import com.google.firebase.appdistribution.FirebaseAppDistribution
 import com.google.firebase.appdistribution.FirebaseAppDistributionException
 import dagger.hilt.android.AndroidEntryPoint
 import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InTouchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = InTouchTheme.colors.mainBlue
                ) {
                    Column {
                        if (BuildConfig.DEBUG) {
                            MainNavHost(navController = rememberNavController())
                        } else {
                            AppNavScreen(
                                startDestination = AuthorizationRouteBranch.route,
                                authStartDestination = Authentication.route
                            )
                        }
                    }
                }
            }
        }
        Timber.tag("CheckAppBuild").d("Check App Build 001")
    }

    override fun onResume() {
        super.onResume()

        val firebaseAppDistribution = FirebaseAppDistribution.getInstance()
        firebaseAppDistribution.updateIfNewReleaseAvailable()
            .addOnProgressListener { updateProgress ->
                Timber.tag("APP_DISTRIBUTION")
                    .d("SUCCESS: UPDATE PROGRESS $updateProgress")
            }
            .addOnFailureListener { e ->
                if (e is FirebaseAppDistributionException) {
                    when (e.errorCode) {
                        FirebaseAppDistributionException.Status.NOT_IMPLEMENTED -> {
                            Timber.tag("APP_DISTRIBUTION")
                                .d("FAILURE: SDK DID NOTHING. ${e.message}")
                        }

                        else -> {
                            Timber.tag("APP_DISTRIBUTION")
                                .d("FAILURE: UNKNOWN ERROR. ${e.message}")
                        }
                    }
                }
            }
    }
}