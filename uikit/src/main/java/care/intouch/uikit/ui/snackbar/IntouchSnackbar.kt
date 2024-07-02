package care.intouch.uikit.ui.snackbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarData
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.TextButton

@Composable
fun IntouchSnackbar(
    modifier: Modifier = Modifier,
    data: SnackbarData
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(
                color = InTouchTheme.colors.mainGreen40
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .weight(1f)
                .padding(
                    vertical = 16.dp,
                    horizontal = 12.dp
                ),
            style = InTouchTheme.typography.bodyRegular,
            color = InTouchTheme.colors.white,
            text = data.visuals.message,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis
        )
        data.visuals.actionLabel?.let { actionLabel ->
            TextButton(
                modifier = Modifier.padding(end = 4.dp),
                text = StringVO.Plain(actionLabel),
                onClick = {
                    data.performAction()
                }
            )
        }
    }
}

@Preview
@Composable
private fun IntouchSnackbarPreview(){
    InTouchTheme{
        IntouchSnackbar(data = TestPreviewSnackbarData())
    }
}

class TestPreviewSnackbarData(
    override val visuals: SnackbarVisuals = TestSnackbarVisuals(),
    val onDismiss: () -> Unit = { },
    val onPerformAction: () -> Unit = { }
) : SnackbarData {
    override fun dismiss() {
        onDismiss()
    }

    override fun performAction() {
        onPerformAction()
    }
}

class TestSnackbarVisuals(
    override val actionLabel: String = "Action",
    override val duration: SnackbarDuration = SnackbarDuration.Indefinite,
    override val message: String = "Snackbars inform users",
    override val withDismissAction: Boolean = true
): SnackbarVisuals
