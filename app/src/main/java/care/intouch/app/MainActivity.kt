package care.intouch.app

import android.content.Intent
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
import care.intouch.app.core.utils.mappers.DeepLinkResultWrapper
import care.intouch.app.core.utils.mappers.DeepLinksMapper
import care.intouch.uikit.theme.InTouchTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var deepLinksMapper: DeepLinksMapper

    private var deepLinkResultWrapper: DeepLinkResultWrapper = DeepLinkResultWrapper.AbsentDeepLink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (intent?.action == Intent.ACTION_VIEW) {
            deepLinkResultWrapper = deepLinksMapper.handleDeepLink(data = intent?.data)
        }

        setContent {
            InTouchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = InTouchTheme.colors.mainBlue
                ) {
                    Column {
                        if (BuildConfig.DEBUG) {
                            when(deepLinkResultWrapper) {
                                is DeepLinkResultWrapper.ResetPasswordDeepLink -> {
                                    AppNavScreen(
                                        startDestination = AuthorizationRouteBranch.route,
                                        authStartDestination = Registration.route
                                    )
                                }
                                is DeepLinkResultWrapper.AbsentDeepLink -> {
                                    MainNavHost(navController = rememberNavController())
                                }
                            }
                        } else {
                            val currentRoute = when(deepLinkResultWrapper) {
                                DeepLinkResultWrapper.ResetPasswordDeepLink -> Registration.route
                                DeepLinkResultWrapper.AbsentDeepLink -> Authentication.route
                            }
                            AppNavScreen(
                                startDestination = AuthorizationRouteBranch.route,
                                authStartDestination = currentRoute
                            )
                        }
                    }
                }
            }
        }
    }
}