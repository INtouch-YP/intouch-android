package care.intouch.app.ui.uiKitSamples.samples

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.navigation.CustomBottomNavBar
import care.intouch.uikit.ui.navigation.CustomTopBar

@Composable
@Preview(showBackground = true)
fun NavigationSamplePreview() {
    InTouchTheme {
        NavigationAppSample()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationAppSample() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopBar(
                onBackArrowClick = { /*TODO*/ },
                onCloseButtonClick = { /*TODO*/ },
                title = "Title Large",
                enabledArcButton = false,
                addBackArrowButton = true,
                addCloseButton = true
            )
        },
        bottomBar = {
            CustomBottomNavBar()
        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(InTouchTheme.colors.mainBlue),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Work space")
        }
    }
}