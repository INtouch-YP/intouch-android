package care.intouch.app.feature.questions.presentations.ui.introductory

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.R
import care.intouch.app.feature.questions.presentations.ui.models.IntroductoryState
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.questions.TopPanel

@Composable
fun IntroductoryQuestionsScreen(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    viewModel: IntroductoryViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    IntroductoryQuestionsScreen(
        onBackClick = onBackClick,
        onNextClick = onNextClick,
        onEvent = {
            viewModel.onEvent()
        },
        state = state
    )
}

@Composable
private fun IntroductoryQuestionsScreen(
    onBackClick: () -> Unit,
    onNextClick: () -> Unit,
    onEvent: () -> Unit,
    state: IntroductoryState
) {
    Box (
        modifier = Modifier.background(InTouchTheme.colors.mainBlue)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            TopPanel(
                backButtonClick = {
                    onBackClick.invoke() },
                text = StringVO.Resource(resId = R.string.name_task_mock)
            )
            Spacer(modifier = Modifier.height(51.dp))
            Text(
                text = StringVO.Resource(resId = R.string.description_task_mock).value(),
                modifier = Modifier.padding(horizontal = 28.dp),
                color = InTouchTheme.colors.textBlue,
                style = InTouchTheme.typography.bodySemibold
            )
            Spacer(modifier = Modifier.height(26.dp))
            Box(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 28.dp)
                .clip(RoundedCornerShape(8.dp))
            ) {
                Image(
                    painter = painterResource(id = care.intouch.uikit.R.drawable.task_image_mock),
                    contentDescription = "task image on introductory questions screen",
                    contentScale = ContentScale.Fit,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))
            IntouchButton(
                onClick = {
                    onNextClick.invoke()
                },
                modifier = Modifier,
                text = StringVO.Resource(R.string.next_button)
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun IntroductoryQuestionsScreenPreview() {
    val state = IntroductoryState("")
    InTouchTheme {
        IntroductoryQuestionsScreen(
            onBackClick = {},
            onNextClick = {},
            onEvent = {},
            state = state
        )
    }
}