import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import care.intouch.app.R
import care.intouch.app.UikitSampleButton
import care.intouch.app.ui.uiKitSamples.ScreenSample
import care.intouch.app.ui.uiKitSamples.samples.MultilineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.OneLineTextFieldSampleScreen
import care.intouch.app.ui.uiKitSamples.samples.ToggleSampleScreen
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.navigation.CustomBottomNavBar
import care.intouch.uikit.ui.navigation.CustomTopBar

@Composable
fun UiKitSample() {

    var screenSample by remember {
        mutableStateOf<ScreenSample>(ScreenSample.MainSampleMenu)
    }

    when (screenSample) {
        ScreenSample.MainSampleMenu -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                UikitSampleButton(
                    text = "Go to one line text field sample",
                    onClick = { screenSample = ScreenSample.OneLineTexFieldSample }
                )

                UikitSampleButton(
                    text = "Go to multiline text field sample",
                    onClick = { screenSample = ScreenSample.MultilineTexFieldSample }
                )

                UikitSampleButton(
                    text = "Go to navigation sample",
                    onClick = { screenSample = ScreenSample.NavigationSample }
                )

                UikitSampleButton(
                    text = "Go to toggle sample",
                    onClick = { screenSample = ScreenSample.ToggleSample }
                )
            }
        }

        ScreenSample.OneLineTexFieldSample -> {
            OneLineTextFieldSampleScreen()
        }

        ScreenSample.MultilineTexFieldSample -> {
            MultilineTextFieldSampleScreen()
        }

        ScreenSample.NavigationSample -> {
            NavigationSample()
        }

        ScreenSample.ToggleSample -> {
            ToggleSampleScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UiKitSamplePreview() {
    InTouchTheme {
        UiKitSample()
    }
}

@Composable
@Preview(showBackground = true)
fun NavigationSamplePreview() {
    InTouchTheme {
        NavigationSample()
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NavigationSample() {
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
            CustomBottomNavBar(
                onFocusTint = InTouchTheme.colors.mainGreen,
                outFocusTint = InTouchTheme.colors.mainGreen40,
                firstItemText = stringResource(id = R.string.home_bottom_nav_bar),
                secondItemText = stringResource(id = R.string.my_progress_bottom_nav_bar),
                thirdItemText = stringResource(id = R.string.my_plan_bottom_nav_bar),
                fourthItemText = stringResource(id = R.string.additional_bottom_nav_bar),
                firstItemImage = painterResource(id = care.intouch.uikit.R.drawable.icon_home),
                secondItemImage = painterResource(id = care.intouch.uikit.R.drawable.icon_progress),
                thirdItemImage = painterResource(id = care.intouch.uikit.R.drawable.icon_plan),
                fourthItemImage = painterResource(id = care.intouch.uikit.R.drawable.icon_additional),
                firstItemClick = {},
                secondItemClick = {},
                thirdItemClick = {},
                fourthItemClick = {},
                onPlusItemClick = {}
            )
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