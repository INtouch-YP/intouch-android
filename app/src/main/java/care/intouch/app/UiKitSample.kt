package care.intouch.app

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.common.utils.ScreenSample
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.navigation.CustomBottomNavBar
import care.intouch.uikit.ui.navigation.CustomTopBar

@Composable
fun UiKitSample() {

    var screenSample by remember {
        mutableStateOf<ScreenSample>(ScreenSample.MainSampleMenu)
    }

    when(screenSample) {
        ScreenSample.MainSampleMenu -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { /*TODO moved to Custom Title*/ },
                    text = "Custom Title",
                    style = InTouchTheme.typography.bodyRegularTypography,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .clickable { /*TODO moved to Custom Button*/ },
                    text = "Custom Button",
                    style = InTouchTheme.typography.bodyRegularTypography,
                    textAlign = TextAlign.Center
                )
                UikitSampleButton(
                    text = "Go to navigation sample",
                    onClick = { screenSample = ScreenSample.NavigationSample }
                )
            }
        }
        ScreenSample.NavigationSample -> {
            NavigationSample()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun UiKitSamplePreview() {
    UiKitSample()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview(showBackground = true)
@Composable
fun NavigationSample() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { CustomTopBar(
            onBackArrowClick = { /*TODO*/ },
            onCloseButtonClick = { /*TODO*/ },
            title = "Title Large"
        ) },
        bottomBar = { CustomBottomNavBar() }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(InTouchTheme.colors.mainColorBlue),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Work space")
        }
    }
}