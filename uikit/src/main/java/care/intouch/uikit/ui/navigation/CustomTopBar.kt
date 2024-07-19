package care.intouch.uikit.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun TopBarArcButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick.invoke() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.arc_rectangle),
            contentDescription = null,
            tint = if (enabled) InTouchTheme.colors.accentGreen else InTouchTheme.colors.accentGreen50
        )
        Icon(
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = null,
            tint = InTouchTheme.colors.input
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TopBarArcButtonPreview() {
    InTouchTheme {
        TopBarArcButton(
            onClick = {},
            enabled = false
        )
    }
}


// Navigation UI
@Composable
fun CustomTopBar(
    onBackArrowClick: () -> Unit,
    onCloseButtonClick: () -> Unit,
    title: String = "",
    titleStyle: TextStyle = InTouchTheme.typography.titleLarge,
    enabledArcButton: Boolean,
    addBackArrowButton: Boolean,
    addCloseButton: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(44.dp)
            .background(InTouchTheme.colors.transparent),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        if (addBackArrowButton) {
            Box(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .size(44.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_left),
                    contentDescription = null,
                    tint = InTouchTheme.colors.mainGreen,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = { onBackArrowClick.invoke() }
                        ),
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .size(44.dp)
                    .background(Color.Transparent)
            )
        }

        Text(
            text = title,
            style = titleStyle,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            color = InTouchTheme.colors.textBlue,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )

        if (addCloseButton) {
            TopBarArcButton(
                onClick = onCloseButtonClick,
                enabled = enabledArcButton,
                modifier = Modifier.padding(end = 28.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(end = 28.dp)
                    .size(47.dp)
                    .background(Color.Transparent)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CustomTopBarPreview() {
    InTouchTheme {
        CustomTopBar(
            onBackArrowClick = { /*TODO*/ },
            onCloseButtonClick = { /*TODO*/ },
            title = "Title Large",
            enabledArcButton = false,
            addBackArrowButton = true,
            addCloseButton = true
        )
    }
}