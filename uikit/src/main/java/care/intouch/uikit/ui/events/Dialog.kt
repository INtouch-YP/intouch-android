package care.intouch.uikit.ui.events

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.SecondaryButtonDark

@Composable
fun Dialog(
    modifier: Modifier,
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogHeaderText: String,
    headerTextStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    dialogMessageText: String,
    dialogTextStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    dismissButtonText: String,
    dismissButtonTextStyle: TextStyle = InTouchTheme.typography.titleMedium,
    confirmButtonText: String,
    confirmButtonTextStyle: TextStyle = InTouchTheme.typography.titleMedium,
    backgroundColor: Color = InTouchTheme.colors.mainBlue
) {
    Column(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier
                .padding(top = 48.dp)
                .padding(horizontal = 28.dp),
            text = dialogHeaderText,
            textAlign = TextAlign.Center,
            style = headerTextStyle,
            color = InTouchTheme.colors.textBlue
        )
        Text(
            modifier = Modifier
                .padding(top = 4.dp)
                .padding(horizontal = 28.dp),
            text = dialogMessageText,
            textAlign = TextAlign.Center,
            style = dialogTextStyle,
            color = InTouchTheme.colors.textBlue
        )
        IntouchButton(
            onClick = onConfirmation,
            modifier = Modifier
                .padding(top = 48.dp),
            text = StringVO.Plain(confirmButtonText),
            textStyle = confirmButtonTextStyle
        )
        SecondaryButtonDark(
            onClick = onDismissRequest,
            modifier = Modifier.padding(top = 4.dp, bottom = 34.dp),
            text = StringVO.Plain(dismissButtonText),
            textStyle = dismissButtonTextStyle,
            isEnabled = true,
        )
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun ConformationDialogPreview() {
    InTouchTheme {
        Dialog(
            modifier = Modifier.padding(horizontal = 28.dp),
            onDismissRequest = { },
            onConfirmation = {},
            dialogHeaderText = buildString {
                append("Are you sure you want \n")
                append("to delete this task?\n")
            },
            dialogMessageText = buildString {
                append("All your entered data will be\n")
                append("permanently removed.")
            },
            dismissButtonText = "Cancel",
            confirmButtonText = "Yes, delete"
        )
    }
}