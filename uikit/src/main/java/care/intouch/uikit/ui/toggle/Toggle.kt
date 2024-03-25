package care.intouch.uikit.ui.toggle

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun Toggle(
    modifier: Modifier = Modifier,
    handleWidth: Dp = 27.dp,
    handleHeight: Dp = 27.dp,
    trackWidth: Dp = 51.dp,
    trackHeight: Dp = 31.dp,
    isActive: Boolean,
    isChecked: Boolean,
    onChecked: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .width(trackWidth)
            .height(trackHeight)
            .clip(CircleShape)
            .background(
                if(isActive) InTouchTheme.colors.mainGreen
                else InTouchTheme.colors.unableElementDark
            ),
        horizontalArrangement = if(isChecked) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = modifier
                .padding(2.dp)
                .width(handleWidth)
                .height(handleHeight)
                .clip(CircleShape)
                .background(
                    if(isActive) InTouchTheme.colors.mainBlue
                    else InTouchTheme.colors.unableElementLight
                )
                .align(Alignment.CenterVertically)
                .clickable {
                    onChecked.invoke(!isChecked)
                }
        )
    }
}

@Preview
@Composable
fun TogglePreview() {
    var isChecked by remember { mutableStateOf(true) }

    InTouchTheme {
        Toggle(
            isActive = isChecked,
            isChecked = isChecked
        ) {
            isChecked = it
        }
    }
}
