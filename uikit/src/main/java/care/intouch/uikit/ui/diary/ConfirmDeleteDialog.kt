package care.intouch.uikit.ui.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.buttons.SecondaryButtonDark

@Composable
fun ConfirmDeleteDialog(
    modifier: Modifier = Modifier,
    onConfirm: () -> Unit,
    onCancel: () -> Unit,
    deleteQuestion: StringVO,
    deleteWarning: StringVO,
    confirmButtonText: StringVO,
    cancelButtonText: StringVO,
    backgroundColor: Color = InTouchTheme.colors.mainBlue
) {
    Popup(onDismissRequest = onCancel) {
        Box(
            modifier = modifier
                .fillMaxSize()
                .background(Color.White.copy(alpha = 0.5f)),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = modifier
                    .padding(horizontal = 28.dp)
                    .wrapContentSize()
                    .background(backgroundColor, RoundedCornerShape(20.dp))
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(horizontal = 28.dp, vertical = 36.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        style = InTouchTheme.typography.bodySemibold,
                        text = deleteQuestion.value()
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        textAlign = TextAlign.Center,
                        style = InTouchTheme.typography.bodySemibold,
                        text = deleteWarning.value()
                    )
                    Spacer(modifier = Modifier.height(48.dp))
                    Column(
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        PrimaryButtonGreen(
                            modifier = modifier,
                            onClick = { onConfirm() },
                            text = confirmButtonText
                        )
                        SecondaryButtonDark(
                            modifier = Modifier,
                            onClick = { onCancel() },
                            text = cancelButtonText,
                            textStyle = InTouchTheme.typography.titleSmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ConfirmDeleteDialogPreview() {
    ConfirmDeleteDialog(
        onConfirm = {},
        onCancel = {},
        deleteQuestion = StringVO.Plain("Are you sure you want\n to delete this note?"),
        deleteWarning = StringVO.Plain("All your entered data will be\n permanently removed."),
        confirmButtonText = StringVO.Plain("Yes, delete"),
        cancelButtonText = StringVO.Plain("Cancel")

    )
}