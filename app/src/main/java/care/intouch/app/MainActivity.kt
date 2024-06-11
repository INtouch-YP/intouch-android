package care.intouch.app

import android.content.Intent
import android.net.Uri
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
import care.intouch.app.core.navigation.Registration
import care.intouch.app.core.navigation.navhost.MainNavHost
import care.intouch.uikit.theme.InTouchTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var isResetPasswordDeepLink = false

        if (intent?.action == Intent.ACTION_VIEW) {
            val data: Uri? = intent?.data

            val resetUrlPattern = "$BASE_URL$RESET_PASSWORD_ENDPOINT.*".toRegex()

            if (data.toString().matches(resetUrlPattern)) {
                isResetPasswordDeepLink = true
            }
        }

        setContent {
            InTouchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = InTouchTheme.colors.mainBlue
                ) {
                    Column {
                        if (BuildConfig.DEBUG) {
                            if (isResetPasswordDeepLink) {
                                AppNavScreen(
                                    startDestination = AuthorizationRouteBranch.route,
                                    authStartDestination = Registration.route
                                )
                            } else {
                                MainNavHost(navController = rememberNavController())
                            }
                        } else {
                            AppNavScreen(
                                startDestination = AuthorizationRouteBranch.route,
                                authStartDestination = if (isResetPasswordDeepLink) Registration.route
                                else Authentication.route
                            )
                        }
                    }
                }
            }
        }
    }

    companion object {
        const val BASE_URL = "https://app.intouch.care"
        const val RESET_PASSWORD_ENDPOINT = "/reset-password/"
    }
}