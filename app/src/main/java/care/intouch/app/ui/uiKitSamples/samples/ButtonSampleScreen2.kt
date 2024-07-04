package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.DeleteButton
import care.intouch.uikit.ui.buttons.IconicButtonCircle
import care.intouch.uikit.ui.buttons.IconicTabBarPlus
import care.intouch.uikit.ui.buttons.TertiaryButtonDefault
import care.intouch.uikit.ui.navigation.TopBarArcButton

@Composable
fun ButtonSampleScreenSecond() {
    Surface(
        color = InTouchTheme.colors.mainBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            IconicButtonCircle(
                onClick = {},
                modifier = Modifier.padding(top = 5.dp)
            )
            IconicTabBarPlus(
                onClick = {},
                modifier = Modifier.padding(top = 5.dp)
            )
            TopBarArcButton(
                onClick = { },
                enabled = false,
                modifier = Modifier.padding(top = 5.dp),
            )
            TopBarArcButton(
                onClick = { },
                enabled = true,
                modifier = Modifier.padding(top = 5.dp),
            )
            TertiaryButtonDefault(
                onClick = {},
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(334.dp)
                    .height(42.dp),
                text = "Security",
                isActive = false
            )
            TertiaryButtonDefault(
                onClick = {},
                modifier = Modifier
                    .padding(top = 5.dp)
                    .width(334.dp)
                    .height(42.dp),
                text = "Security",
                isActive = true,
            )
            DeleteButton(
                onClick = {},
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Delete Profile"),
                isActive = false
            )
            DeleteButton(
                onClick = {},
                modifier = Modifier.padding(top = 5.dp),
                text = StringVO.Plain("Delete Profile"),
                isActive = true,
            )
        }
    }
}