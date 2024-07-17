package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.buttons.PrimaryButtonStroke
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.buttons.SecondaryButtonDark
import care.intouch.uikit.ui.buttons.SecondaryButtonWhite

@Composable
fun ButtonSampleScreen() {
    Surface(
        color = InTouchTheme.colors.mainBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            PrimaryButtonGreen(
                onClick = { },
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Call to action")
            )
            PrimaryButtonGreen(
                onClick = { },
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Call to action"),
                isEnabled = false
            )
            PrimaryButtonStroke(
                onClick = {},
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Call to action")
            )
            PrimaryButtonWhite(
                onClick = {},
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Call to action")
            )
            SecondaryButtonDark(
                onClick = { },
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Call to action")
            )
            SecondaryButtonWhite(
                onClick = { },
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Call to action")
            )
            SecondaryButtonDark(
                onClick = {},
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Call to action"),
                isEnabled = false
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ButtonSampleScreenPreview() {
    InTouchTheme {
        ButtonSampleScreen()
    }
}