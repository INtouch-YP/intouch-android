package care.intouch.app.ui.uiKitSamples.samples

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
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
import care.intouch.uikit.ui.textFields.ProfileInfoTextField

@Composable
fun ProfileInfoTextFieldScreen() {
    var text by remember { mutableStateOf("Same info") }

    Surface(
        color = InTouchTheme.colors.mainBlue,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
                ProfileInfoTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    enabled = true,
                    naming = "Name",
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                ProfileInfoTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    enabled = false,
                    naming = "Last nameId",
                    modifier = Modifier.padding(bottom = 10.dp)
                )
                ProfileInfoTextField(
                    value = text,
                    onValueChange = {
                        text = it
                    },
                    enabled = false,
                    naming = "E-mail",
                )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileInfoTextFieldScreenPreview() {
    InTouchTheme {
        ProfileInfoTextFieldScreen()
    }
}