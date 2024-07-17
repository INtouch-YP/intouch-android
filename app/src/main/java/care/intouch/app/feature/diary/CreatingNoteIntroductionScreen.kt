package care.intouch.app.feature.diary

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.R
import care.intouch.app.feature.diary.DiaryViewModel
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.PrimaryButtonGreen
import care.intouch.uikit.ui.navigation.CustomTopBar

@Composable
fun CreatingNoteIntroductionScreen(
    onNextClick: () -> Unit,
    onBackButtonClick: () -> Unit
) {
    val viewModel: DiaryViewModel = hiltViewModel()
    val screenState by viewModel.diaryUIState.collectAsState()
    val userName = screenState.userName

    Surface(modifier = Modifier.fillMaxSize(), color = InTouchTheme.colors.mainBlue) {}
    LazyColumn(
        modifier = Modifier
            .fillMaxHeight()
            .padding(top = 8.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            CustomTopBar(
                title = stringResource(id = R.string.emotional_title),
                titleStyle = InTouchTheme.typography.titleMedium,
                onBackArrowClick = { onBackButtonClick.invoke() },
                onCloseButtonClick = { },
                enabledArcButton = false,
                addBackArrowButton = true,
                addCloseButton = false
            )
        }
        item {
            Text(
                modifier = Modifier.padding(horizontal = 28.dp, vertical = 12.dp),
                style = InTouchTheme.typography.bodySemibold,
                color = InTouchTheme.colors.textGreen,
                text = String.format(
                    stringResource(id = R.string.diary_introduction_text),
                    userName
                )
            )
        }

        item {
            Image(
                painter = ImageVO.Resource(care.intouch.uikit.R.drawable.illustartion_make_note_introduction)
                    .painter(), contentDescription = "Lotus man"
            )
        }
        item {
            PrimaryButtonGreen(
                modifier = Modifier.padding(top = 30.dp),
                onClick = { onNextClick.invoke() },
                isEnabled = true,
                text = StringVO.Resource(care.intouch.app.R.string.next_button)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CreatingNoteIntroductionScreenPreview() {
    InTouchTheme {
        CreatingNoteIntroductionScreen(
            onNextClick = {},
            onBackButtonClick = {}
        )
    }
}