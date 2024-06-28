package care.intouch.app.feature.pinCode.ui.confirm

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
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
import androidx.compose.ui.tooling.preview.Preview
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

@Preview
@Composable
fun PinCodeConfirmationScreen(
    modifier: Modifier = Modifier,
    onSaveClick: () -> Unit = {},
    onSkipClick: () -> Unit = {},
    onBackClick: () -> Unit = {},
    viewModel: PinCodeConfirmationViewModel = hiltViewModel(),
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    var pinCode by rememberSaveable { mutableStateOf("") }
    var isFullPinCode by rememberSaveable { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }

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
        Icon(
            modifier = Modifier
                .padding(start = 28.dp, top = 12.dp)
                .clickable { onBackClick() },
            painter = ImageVO.Resource(R.drawable.icon_arrow_left).painter(),
            contentDescription = null,
            tint = InTouchTheme.colors.mainGreen
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 34.dp)
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = StringVO.Resource(care.intouch.app.R.string.confirm_pin_title).value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                color = InTouchTheme.colors.textBlue
            )

            Spacer(modifier = Modifier.height(88.dp))
            Text(
                text = StringVO.Resource(care.intouch.app.R.string.enter_pin_sub_title).value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                color = InTouchTheme.colors.textBlue
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column {
                PinCodeInputField(value = pinCode, onValueChange = {
                    pinCode = it
                    viewModel.onEvent(PinCodeConfirmationEvent.Entering(it))
                })

                when (state) {

                    is PinCodeConfirmationScreenState.Confirmed -> {
                        onSaveClick()
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    is PinCodeConfirmationScreenState.Default -> {
                        isFullPinCode = (state as PinCodeConfirmationScreenState.Default).isFullPinCode
                        Spacer(modifier = Modifier.height(28.dp))
                    }

                    is PinCodeConfirmationScreenState.NotConfirmed -> {
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            modifier = Modifier.height(21.dp),
                            text = StringVO.Resource(care.intouch.app.R.string.passwords_do_not_match)
                                .value(),
                            style = InTouchTheme.typography.caption1Semibold,
                            textAlign = TextAlign.Start,
                            color = InTouchTheme.colors.errorRed
                        )

                        Spacer(modifier = Modifier.height(11.dp))
                    }
                }
            }

            IntouchButton(
                onClick = {
                    viewModel.onEvent(PinCodeConfirmationEvent.Statement(pinCode))
                    pinCode = ""
                    isFullPinCode = false
                },
                modifier = Modifier,
                text = StringVO.Resource(care.intouch.app.R.string.save_button).value(),
                isEnabled = isFullPinCode
            )

            Spacer(modifier = Modifier.height(2.dp))
            PrimaryButtonWhite(
                onClick = {
                    viewModel.onEvent(PinCodeConfirmationEvent.Skip)
                    onSkipClick()
                },
                modifier = Modifier,
                text = StringVO.Resource(care.intouch.app.R.string.skip_button).value()
            )
        }
    }
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}