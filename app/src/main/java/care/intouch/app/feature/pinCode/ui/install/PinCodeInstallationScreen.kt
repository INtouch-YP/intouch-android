package care.intouch.app.feature.pinCode.ui.install

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.PrimaryButtonWhite
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputField


@Composable
fun PinCodeInstallationScreen(
    onSaveClick: (argument: String) -> Unit,
    onSkipClick: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: PinCodeInstallationViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var pinCode by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var isFullPinCode by rememberSaveable { mutableStateOf(false) }

    when (state) {
        is PinCodeInstallationScreenState.Default -> {
            isFullPinCode = false
        }

        is PinCodeInstallationScreenState.FullPinCode -> {
            isFullPinCode = true
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input)
    ) {
        Image(
            modifier = Modifier.height(76.dp),
            painter = ImageVO.Resource(R.drawable.head_background_small).painter(),
            contentDescription = "header",
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .focusRequester(focusRequester)
                .padding(top = 70.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = StringVO.Resource(care.intouch.app.R.string.create_pin_title).value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                color = InTouchTheme.colors.textBlue
            )

            Spacer(modifier = Modifier.height(18.dp))
            Text(
                modifier = Modifier.height(42.dp),
                text = StringVO.Resource(care.intouch.app.R.string.info_about_setup_pin).value(),
                style = InTouchTheme.typography.bodySemibold,
                textAlign = TextAlign.Center,
                color = InTouchTheme.colors.textGreen
            )

            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = StringVO.Resource(care.intouch.app.R.string.enter_pin_sub_title).value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                color = InTouchTheme.colors.textBlue
            )
            Spacer(modifier = Modifier.height(12.dp))
            PinCodeInputField(
                value = pinCode,
                onValueChange = {
                    pinCode = it
                    viewModel.onEvent(PinCodeInstallationEvent.Entering(it))
                })

            Spacer(modifier = Modifier.height(28.dp))
            IntouchButton(
                onClick = { onSaveClick(pinCode) },
                modifier = Modifier,
                text = StringVO.Resource(care.intouch.app.R.string.save_button),
                isEnabled = isFullPinCode
            )

            Spacer(modifier = Modifier.height(2.dp))
            PrimaryButtonWhite(
                onClick = {
                    viewModel.onEvent(PinCodeInstallationEvent.Skip)
                    onSkipClick()
                },
                modifier = Modifier,
                text = StringVO.Resource(care.intouch.app.R.string.skip_button)
            )
        }
    }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}