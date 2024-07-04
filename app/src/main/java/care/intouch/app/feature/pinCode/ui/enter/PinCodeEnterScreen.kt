package care.intouch.app.feature.pinCode.ui.enter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import care.intouch.app.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.buttons.SecondaryButtonDark
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputField

@Composable
fun PinCodeEnterScreen(
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    onForgotPicCodeClick: () -> Unit,
    viewModel: PinCodeEnterViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    var pinCode by rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var isFullPinCode by rememberSaveable { mutableStateOf(false) }

    var openDialogPinCode by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(InTouchTheme.colors.input),
        verticalArrangement = Arrangement.Top
    ) {
        Image(
            modifier = Modifier.height(76.dp),
            painter = ImageVO.Resource(care.intouch.uikit.R.drawable.head_background_small)
                .painter(),
            contentDescription = "header",
            contentScale = ContentScale.FillBounds,
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp)
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = StringVO.Resource(R.string.pin_code).value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                color = InTouchTheme.colors.textBlue
            )

            Spacer(modifier = Modifier.height(38.dp))

            when (state) {

                PinCodeEnterState.Confirmed -> {
                    onNextClick()
                }

                PinCodeEnterState.Default -> {
                    isFullPinCode = false
                    Text(
                        text = StringVO.Resource(R.string.enter_pin_sub_title).value(),
                        style = InTouchTheme.typography.bodyRegular,
                        textAlign = TextAlign.Center,
                        color = InTouchTheme.colors.textBlue
                    )
                }

                PinCodeEnterState.NotConfirmed -> {
                    isFullPinCode = false
                    Text(
                        text = StringVO.Resource(R.string.incorrect_error_pin).value(),
                        style = InTouchTheme.typography.bodyRegular,
                        textAlign = TextAlign.Center,
                        color = InTouchTheme.colors.errorRed
                    )
                }

                PinCodeEnterState.FullPinCode -> {
                    isFullPinCode = true
                    Text(
                        text = StringVO.Resource(R.string.enter_pin_sub_title).value(),
                        style = InTouchTheme.typography.bodyRegular,
                        textAlign = TextAlign.Center,
                        color = InTouchTheme.colors.textBlue
                    )
                }
            }
            Spacer(modifier = Modifier.height(12.dp))

            PinCodeInputField(value = pinCode, onValueChange = {
                pinCode = it
                viewModel.onEvent(PinCodeEnterEvent.Entering(it))
            })
            Spacer(modifier = Modifier.height(28.dp))

            IntouchButton(
                onClick = {
                    viewModel.onEvent(PinCodeEnterEvent.Enter)
                    pinCode = ""
                },
                modifier = Modifier,
                text = StringVO.Resource(R.string.next_button),
                isEnabled = isFullPinCode
            )
            Spacer(modifier = Modifier.height(2.dp))

            SecondaryButtonDark(
                onClick = {
                    openDialogPinCode = true
                },
                textStyle = InTouchTheme.typography.bodySemibold,
                modifier = Modifier,
                text = StringVO.Resource(R.string.forgot_pin_title),
            )
            if (openDialogPinCode) {
                DialogPinCode(
                    onDismissRequest = { openDialogPinCode = false },
                    onForgotPinCodeClick = onForgotPicCodeClick
                )
            }
        }
    }

    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

@Composable
fun DialogPinCode(
    onDismissRequest: () -> Unit, onForgotPinCodeClick: () -> Unit
) {
    Dialog(onDismissRequest = { onDismissRequest() }) {
        Column(
            modifier = Modifier
                .height(304.dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(InTouchTheme.colors.dialog),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier.padding(top = 50.dp),
                style = InTouchTheme.typography.bodyBold,
                color = InTouchTheme.colors.textBlue,
                text = StringVO.Resource(R.string.forgot_your_pin_title).value()
            )
            Spacer(modifier = Modifier.height(18.dp))

            Text(
                modifier = Modifier.padding(horizontal = 18.dp),
                style = InTouchTheme.typography.bodySemibold,
                color = InTouchTheme.colors.textGreen,
                text = StringVO.Resource(R.string.create_new_pin_title).value(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(28.dp))

            IntouchButton(
                onClick = { onForgotPinCodeClick() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                text = StringVO.Resource(R.string.log_in_again),
                contentPadding = PaddingValues(
                    vertical = 14.dp
                )
            )
            Spacer(modifier = Modifier.height(4.dp))

            IntouchButton(
                onClick = { onDismissRequest() },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp),
                text = StringVO.Resource(R.string.cancel_button),
                contentPadding = PaddingValues(
                    horizontal = 78.dp, vertical = 14.dp
                ),
                enableBackgroundColor = Color.Transparent,
                enableTextColor = InTouchTheme.colors.textBlue
            )
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewDialog() {
    InTouchTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            DialogPinCode(onDismissRequest = {}, onForgotPinCodeClick = {})
        }
    }
}