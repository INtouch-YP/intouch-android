package care.intouch.uikit.ui.events

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton


@Composable
fun OneButtonDialog(
    modifier: Modifier,
    dialogHeaderText: String,
    headerTextStyle: TextStyle = InTouchTheme.typography.titleBoldLarge,
    headerTextColor: Color = InTouchTheme.colors.textBlue,
    dialogMessageText: String,
    dialogTextStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    backgroundColor: Color = InTouchTheme.colors.white,
    dialogImage: Int,
    confirmButtonText: String,
    confirmButtonTextStyle: TextStyle = InTouchTheme.typography.titleMedium,
    onConfirmation: () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            modifier = Modifier.size(height = 180.dp, width = 156.dp),
            painter = painterResource(id = dialogImage),
            contentDescription = "dialog image",
            contentScale = ContentScale.Fit
        )
        Text(
            modifier = Modifier
                .padding(top = 48.dp)
                .padding(horizontal = 28.dp),
            text = dialogHeaderText,
            textAlign = TextAlign.Center,
            style = headerTextStyle,
            color = headerTextColor
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .padding(horizontal = 68.dp),
            text = dialogMessageText,
            textAlign = TextAlign.Center,
            style = dialogTextStyle,
            color = InTouchTheme.colors.textBlue
        )
        IntouchButton(
            onClick = onConfirmation,
            modifier = Modifier
                .padding(top = 48.dp)
                .wrapContentHeight(),
            contentPadding = PaddingValues(horizontal = 90.dp, vertical = 20.dp),
            text = StringVO.Plain(confirmButtonText),
            textStyle = confirmButtonTextStyle
        )
    }

}

@Composable
@Preview
fun OneButtonDialogPreview() {
    InTouchTheme {
        OneButtonDialog(
            modifier = Modifier.fillMaxSize(),
            dialogHeaderText = "Oooops!",
            dialogMessageText = buildString {
                append("Unable to establish a connection.\n")
                append("Please check your internet connection and try again")
            },
            backgroundColor = InTouchTheme.colors.mainBlue,
            dialogImage = R.drawable.illustration_internet_connection_lost,
            confirmButtonText = "Try again",
            onConfirmation = {}
        )
    }
}