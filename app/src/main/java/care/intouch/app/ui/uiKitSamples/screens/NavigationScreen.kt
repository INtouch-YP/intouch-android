package care.intouch.app.ui.uiKitSamples.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Button
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun NavigationScreen(
    goToAuthenticationScreen: () -> Unit,
    goToRegistrationScreen: () -> Unit,
    goToPinCodeScreen: () -> Unit,
    goToMainScreen: () -> Unit
) {
    var entryPoint: EntryPoint by remember {
        mutableStateOf(EntryPoint.AUTHENTICATION)
    }

    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .fillMaxSize()
            .selectableGroup(),
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            modifier = Modifier
                .padding(top = 64.dp, bottom = 64.dp)
                .align(Alignment.CenterHorizontally),
            text = "Navigation Debug Screen Mode",
            style = InTouchTheme.typography.titleSmall
        )

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = entryPoint == EntryPoint.AUTHENTICATION,
                onClick = { entryPoint = EntryPoint.AUTHENTICATION },
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Authentication",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = entryPoint == EntryPoint.REGISTRATION,
                onClick = { entryPoint = EntryPoint.REGISTRATION },
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Registration",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = entryPoint == EntryPoint.PIN_CODE,
                onClick = { entryPoint = EntryPoint.PIN_CODE },
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "Enter Pin Code",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            RadioButton(
                selected = entryPoint == EntryPoint.USER_IS_AUTHENTICATED,
                onClick = { entryPoint = EntryPoint.USER_IS_AUTHENTICATED },
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )

            Text(
                text = "User is Authenticated",
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 8.dp, end = 8.dp, top = 64.dp),
            onClick = {
                when(entryPoint) {
                    EntryPoint.AUTHENTICATION -> { goToAuthenticationScreen.invoke() }
                    EntryPoint.REGISTRATION -> { goToRegistrationScreen.invoke() }
                    EntryPoint.PIN_CODE -> { goToPinCodeScreen.invoke() }
                    EntryPoint.USER_IS_AUTHENTICATED -> { goToMainScreen.invoke() }
                }
            }
        ) {
            Text("Go To App Navigation")
        }
    }
}

@Composable
@Preview(showBackground = true)
fun NavigationScreenPreview() {
    InTouchTheme {
        NavigationScreen(
            goToAuthenticationScreen = {},
            goToRegistrationScreen = {},
            goToPinCodeScreen = {},
            goToMainScreen = {}
        )
    }
}

enum class EntryPoint {
    AUTHENTICATION, REGISTRATION, PIN_CODE, USER_IS_AUTHENTICATED
}