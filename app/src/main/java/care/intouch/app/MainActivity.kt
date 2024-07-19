package care.intouch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import care.intouch.app.core.navigation.AppNavScreen
import care.intouch.app.core.navigation.Authentication
import care.intouch.app.core.navigation.AuthorizationRouteBranch
import care.intouch.app.core.navigation.Home
import care.intouch.app.core.navigation.navhost.MainNavHost
import care.intouch.app.models.MainActivitySideEffect
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.snackbar.IntouchSnackbar
import com.google.firebase.appdistribution.FirebaseAppDistribution
import com.google.firebase.appdistribution.FirebaseAppDistributionException
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading
            }
        }
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            var isAuthenticate by remember { mutableStateOf(false) }

            LaunchedEffect(key1 = null) {
                viewModel.sideEffect.collect { sideEffect ->
                    when (sideEffect) {
                        is MainActivitySideEffect.ShowToastWithAction -> {
                            showSnackbar(
                                snackBarHostState = snackBarHostState,
                                message = sideEffect.message,
                                actionMessage = sideEffect.actionMessage,
                                onActionClicked = sideEffect.onActionClicked
                            )
                        }

                        MainActivitySideEffect.NavigatedToAuth -> {
                            isAuthenticate = false
                        }

                        MainActivitySideEffect.NavigatedToMainScreen -> {
                            isAuthenticate = true
                        }
                    }
                }
            }

            InTouchTheme {
                Surface(
                    modifier = Modifier
                        .fillMaxSize()
                        .systemBarsPadding(),
                    color = InTouchTheme.colors.mainBlue
                ) {
                    Column {
                        if (BuildConfig.DEBUG) {

                            MainNavHost(
                                navController = rememberNavController(),
                                isAuthenticate = isAuthenticate
                            )
                        } else {

                            val startDestination = if (isAuthenticate) {
                                Home.route
                            } else {
                                AuthorizationRouteBranch.route
                            }

                            AppNavScreen(
                                startDestination = startDestination,
                                authStartDestination = Authentication.route
                            )
                        }
                    }
                }
            }
            SnackbarHost(
                hostState = snackBarHostState
            ) { snackBarData ->
                IntouchSnackbar(
                    data = snackBarData
                )
            }
        }
    }

    private suspend fun showSnackbar(
        snackBarHostState: SnackbarHostState,
        message: StringVO,
        actionMessage: StringVO,
        onActionClicked: () -> Unit
    ) {
        snackBarHostState.showSnackbar(
            message = message.value(this@MainActivity),
            actionLabel = actionMessage.value(this@MainActivity),
            duration = SnackbarDuration.Indefinite
        ).run {
            when (this) {
                SnackbarResult.Dismissed -> {
                    onActionClicked.invoke()
                }

                SnackbarResult.ActionPerformed -> {
                    onActionClicked.invoke()
                }
            }
        }
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