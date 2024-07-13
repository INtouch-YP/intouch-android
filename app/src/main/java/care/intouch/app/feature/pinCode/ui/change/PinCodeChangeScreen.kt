package care.intouch.app.feature.pinCode.ui.change

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
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
import care.intouch.uikit.ui.pinCodeInput.PinCodeInputField

@Composable
fun PinCodeChangeScreen(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    onSuccessChange: () -> Unit,
) {
    val viewModel: PinCodeChangeViewModel = hiltViewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()

    PinCodeChangeScreen(
        modifier = modifier,
        onSuccessChange = onSuccessChange,
        onCloseClick = onCloseClick,
        onEvent = viewModel::onEvent,
        state = state
    )
}

@Composable
fun PinCodeChangeScreen(
    modifier: Modifier = Modifier,
    onCloseClick: () -> Unit,
    onSuccessChange: () -> Unit,
    onEvent: (event: PinCodeChangeEvent) -> Unit,
    state: PinCodeChangeScreenState
) {
    var pinCode by rememberSaveable { mutableStateOf("") }
    var titleText by rememberSaveable { mutableIntStateOf(care.intouch.app.R.string.enter_old_pin_sub_title) }
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
        Column(
            modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.End
        ) {
            Box(
                modifier = Modifier
                    .padding(top = 8.dp)
                    .padding(end = 32.dp)
                    .clickable { onCloseClick() }, contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = ImageVO.Resource(R.drawable.arc_rectangle).painter(),
                    contentDescription = null,
                    tint = InTouchTheme.colors.accentGreen50
                )
                Icon(
                    painter = ImageVO.Resource(R.drawable.icon_close).painter(),
                    contentDescription = null,
                    tint = InTouchTheme.colors.input
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 18.dp)
                .focusRequester(focusRequester),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Text(
                text = StringVO.Resource(care.intouch.app.R.string.update_pin_profile).value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                fontSize = 24.sp,
                lineHeight = 28.sp,
                color = InTouchTheme.colors.textBlue
            )

            Spacer(modifier = Modifier.height(60.dp))
            Text(
                text = StringVO.Resource(titleText).value(),
                style = InTouchTheme.typography.bodyRegular,
                textAlign = TextAlign.Center,
                color = InTouchTheme.colors.textBlue
            )
            Spacer(modifier = Modifier.height(12.dp))

            Column {
                PinCodeInputField(
                    value = pinCode,
                    onValueChange = {
                        pinCode = it
                        onEvent(PinCodeChangeEvent.Entering(it))
                    },
                    isError = state == PinCodeChangeScreenState.IncorrectPinCode || state == PinCodeChangeScreenState.NotMatchPinCode
                )

                when (state) {
                    PinCodeChangeScreenState.ConfirmNewPinCode -> {
                        Spacer(modifier = Modifier.height(40.dp))
                        titleText = care.intouch.app.R.string.confirm_new_pin_sub_title
                    }

                    PinCodeChangeScreenState.Confirmed -> {
                        onSuccessChange()
                    }

                    PinCodeChangeScreenState.Default -> {
                        Spacer(modifier = Modifier.height(40.dp))
                    }

                    PinCodeChangeScreenState.EnterNewPinCode -> {
                        Spacer(modifier = Modifier.height(40.dp))
                        titleText = care.intouch.app.R.string.enter_new_pin_sub_title
                    }

                    PinCodeChangeScreenState.IncorrectPinCode -> {
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            modifier = Modifier.height(21.dp),
                            text = StringVO.Resource(care.intouch.app.R.string.incorrect_error)
                                .value(),
                            style = InTouchTheme.typography.caption1Semibold,
                            textAlign = TextAlign.Start,
                            color = InTouchTheme.colors.errorRed
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }

                    PinCodeChangeScreenState.NotMatchPinCode -> {
                        Spacer(modifier = Modifier.height(8.dp))

                        Text(
                            modifier = Modifier.height(22.dp),
                            text = StringVO.Resource(care.intouch.app.R.string.passwords_do_not_match)
                                .value(),
                            style = InTouchTheme.typography.caption1Semibold,
                            textAlign = TextAlign.Start,
                            color = InTouchTheme.colors.errorRed
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }
            }

            IntouchButton(
                onClick = {
                    onEvent(PinCodeChangeEvent.Statement(pinCode))
                    pinCode = ""
                },
                modifier = Modifier,
                text = StringVO.Resource(care.intouch.app.R.string.save_button),
                isEnabled = state.isFullPinCode
            )

            Spacer(modifier = Modifier.height(2.dp))
        }
    }
    LaunchedEffect(key1 = Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }
}

@Preview
@Composable
fun PreviewPinCodeChangeScreen() {
    val state: PinCodeChangeScreenState by remember {
        mutableStateOf(PinCodeChangeScreenState.Initial)
    }
    InTouchTheme {
        PinCodeChangeScreen(onCloseClick = { }, onSuccessChange = { }, onEvent = {}, state = state)
    }
}