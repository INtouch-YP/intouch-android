package care.intouch.uikit.ui.titles

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme

//region TitleLarge
@Composable
fun TitleLarge(titleText: String, bodyText: String) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = titleText,
            color = InTouchTheme.colors.textColorBlue,
            style = InTouchTheme.typography.titleLargeTypography,
            modifier = Modifier.padding(horizontal = 28.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = bodyText,
            color = InTouchTheme.colors.textColorBlue,
            style = InTouchTheme.typography.bodyRegularTypography,
            modifier = Modifier.padding(horizontal = 28.dp)
        )
    }
}

@Preview
@Composable
fun PreviewTitleLarge() {
    InTouchTheme {
        TitleLarge(titleText = "Title Large", bodyText = "Body Text")
    }
}
//endregion

//region TitleMediumWithIcon
@Composable
fun TitleMediumWithIcon(
    titleText: String,
    startIcon: Painter,
    startIconDescriptor: String,
    startOnClick: () -> Unit,
    endIcon: Painter = painterResource(id = R.drawable.icon_settings),
    endIconTint: Color,
    endImageDescriptor: String,
    endOnClick: () -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(all = 20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = startOnClick,
            modifier = Modifier
                .padding(start = 28.dp)
                .clip(RoundedCornerShape(20))
                .background(InTouchTheme.colors.unableElementsColorDark)
        ) {
            Icon(
                painter = startIcon, contentDescription = startIconDescriptor
            )
        }
        Text(
            text = titleText,
            color = InTouchTheme.colors.textColorBlue,
            style = InTouchTheme.typography.titleMediumTypography,
            modifier = Modifier
        )
        IconButton(onClick = endOnClick, modifier = Modifier.padding(end = 28.dp)) {
            Icon(painter = endIcon, tint = endIconTint, contentDescription = endImageDescriptor)
        }
    }
}

@Preview
@Composable
fun PreviewTitleMediumWithImage() {
    InTouchTheme {
        TitleMediumWithIcon(
            titleText = "Title medium",
            startIcon = painterResource(id = R.drawable.icon_arrow_left),
            startOnClick = {},
            endImageDescriptor = "",
            endOnClick = {},
            startIconDescriptor = "",
            endIconTint = InTouchTheme.colors.mainColorGreen
        )
    }

}
//endregion

//region TitleMedium
@Composable
fun TitleMedium(titleText: String) {
    Text(
        text = titleText,
        color = InTouchTheme.colors.textColorBlue,
        style = InTouchTheme.typography.titleMediumTypography,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
    )
}

@Preview
@Composable
fun PreviewTitleMedium() {
    InTouchTheme {
        TitleMedium(titleText = "Title Medium")
    }
}
//endregion

//region TitleMediumWithBody
@Composable
fun TitleMediumWithBody(titleText: String, bodyText: String) {
    Column(
        modifier = Modifier.padding(20.dp)
    ) {
        Text(
            text = titleText,
            color = InTouchTheme.colors.textColorBlue,
            style = InTouchTheme.typography.titleMediumTypography
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = bodyText,
            color = InTouchTheme.colors.textColorBlue,
            style = InTouchTheme.typography.bodyRegularTypography
        )
    }
}

@Preview
@Composable
fun PreviewTitleMediumWithBody() {
    InTouchTheme {
        TitleMediumWithBody(titleText = "Title Medium", bodyText = "Caption 1")
    }
}
//endregion

//region BodyBoldTextCentral
@Composable
fun BodyBoldTextCentral(text: String) {
    Text(
        text = text,
        color = InTouchTheme.colors.textColorBlue,
        style = InTouchTheme.typography.bodyBoldTypography,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
    )
}

@Preview
@Composable
fun PreviewBodyBoldTextCentral() {
    InTouchTheme {
        BodyBoldTextCentral(text = "BodyBold Text Central")
    }
}
//endregion

//region Subtitle
@Composable
fun Subtitle(text: String) {
    Text(
        text = text,
        color = InTouchTheme.colors.textColorGreen,
        style = InTouchTheme.typography.subTitleTypography,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(all = 20.dp)
    )
}

@Preview
@Composable
fun PreviewSubtitle() {
    InTouchTheme {
        Subtitle(text = "Subtitle")
    }
}
//endregion