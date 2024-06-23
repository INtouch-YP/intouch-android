package care.intouch.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import care.intouch.app.core.navigation.AppNavScreen
import care.intouch.app.core.navigation.Authentication
import care.intouch.app.core.navigation.AuthorizationRouteBranch
import care.intouch.app.core.navigation.navhost.MainNavHost
import care.intouch.app.models.MainActivitySideEffect
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.snackbar.IntouchSnackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            val snackBarHostState = remember { SnackbarHostState() }
            var isAuthenticate by remember { mutableStateOf(false) }

            val viewModel: MainViewModel = hiltViewModel()
            LaunchedEffect(key1 = null) {
                viewModel.sideEffect.collect {
                    when (it) {
                        is MainActivitySideEffect.ShowToastWithAction -> {
                            snackBarHostState.showSnackbar(
                                message = it.message,
                                actionLabel = getString(R.string.retry_button),
                                duration = SnackbarDuration.Indefinite
                            ).run {
                                when (this) {
                                    SnackbarResult.Dismissed -> {
                                        it.onActionClicked.invoke()
                                    }

                                    SnackbarResult.ActionPerformed -> {
                                        it.onActionClicked.invoke()
                                    }
                                }
                            }
                        }

                        MainActivitySideEffect.NavigatedToAuth -> {
                            isAuthenticate = true
                        }
                    }
                }
            }
            InTouchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = InTouchTheme.colors.mainBlue
                ) {
                    Column {
                        when {
                            BuildConfig.DEBUG -> {
                                MainNavHost(navController = rememberNavController())
                            }

                            else -> {
                                if (isAuthenticate) {
                                    //FIXME: go to main screen
                                    AppNavScreen(
                                        startDestination = AuthorizationRouteBranch.route,
                                        authStartDestination = Authentication.route
                                    )
                                } else {
                                    // FIXME: go to auth screen
                                    MainNavHost(navController = rememberNavController())
                                }
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
    }
}