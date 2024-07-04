package care.intouch.app.feature.profile.presentation.ui.security

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton

@Composable
fun DeleteProfilePopUp(
    modifier: Modifier = Modifier,
    onEvent: (SecurityEvent) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(InTouchTheme.colors.mainBlue)
            .padding(horizontal = 28.dp)
    ) {
        Spacer(modifier = Modifier.height(28.dp))
        Image(
            modifier = Modifier.fillMaxWidth(),
            contentScale = ContentScale.FillHeight,
            painter = painterResource(id = care.intouch.uikit.R.drawable.illustration_delete),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.delete_profile_question),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textGreen
        )
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.delete_profile_warning),
            style = InTouchTheme.typography.bodySemibold,
            textAlign = TextAlign.Center,
            color = InTouchTheme.colors.textGreen
        )
        Spacer(modifier = Modifier.height(40.dp))
        IntouchButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = StringVO.Resource(R.string.cancel_button),
            enableBackgroundColor = InTouchTheme.colors.mainGreen,
            disableBackgroundColor = InTouchTheme.colors.unableElementLight,
            enableTextColor = InTouchTheme.colors.input,
            disableTextColor = InTouchTheme.colors.textGreen40,
            onClick = {
                onEvent.invoke(SecurityEvent.OnCancelDeleteProfile)
            }
        )
        IntouchButton(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            text = StringVO.Resource(R.string.confirm_button),
            enableBackgroundColor = InTouchTheme.colors.transparent,
            disableBackgroundColor = InTouchTheme.colors.transparent,
            enableTextColor = InTouchTheme.colors.textGreen40,
            disableTextColor = InTouchTheme.colors.textGreen40,
            onClick = {
                onEvent.invoke(SecurityEvent.OnDeleteProfile)
            }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun DeleteProfilePopUpPreview() {
    InTouchTheme {
        DeleteProfilePopUp {

        }
    }
}