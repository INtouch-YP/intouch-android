package care.intouch.app.feature.authorization.presentation.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.feature.authorization.presentation.viewModel.PasswordRecoveryViewModel
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun PasswordRecoveryScreen(
    onSendPasswordClick: () -> Unit,
    viewModel: PasswordRecoveryViewModel = hiltViewModel()
) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 24.dp),
            text = "PasswordRecoveryScreen",
            style = InTouchTheme.typography.titleMedium
        )

        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            var emailAddress by remember {
                mutableStateOf("")
            }

            TextField(
                modifier = Modifier.padding(bottom = 24.dp),
                value = emailAddress,
                onValueChange = { newText ->
                    emailAddress = newText
                },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                )
            )

            Button(
                onClick = {
                    onSendPasswordClick.invoke()
                    viewModel.resetPassword(emailAddress)
                }
            ) {
                Text(text = "Send Password")
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun PasswordRecoveryScreenPreview() {
    InTouchTheme {
        PasswordRecoveryScreen(
            onSendPasswordClick = {}
        )
    }
}