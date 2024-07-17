package care.intouch.app.feature.questions.presentations.ui.questions

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import care.intouch.app.R
import care.intouch.app.feature.questions.presentations.ui.models.QuestionsState
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.buttons.IntouchButton
import care.intouch.uikit.ui.navigation.TopBarArcButton
import care.intouch.uikit.ui.questions.PopupQuestions
import care.intouch.uikit.ui.questions.TextFieldQuestion
import care.intouch.uikit.ui.questions.TextFieldWithCheckbox
import care.intouch.uikit.ui.questions.TextFieldWithCheckmars
import care.intouch.uikit.ui.questions.TextFieldWithSliderAndDigits
import care.intouch.uikit.ui.textFields.MultilineTextField
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun QuestionsScreen(
    onCloseClick: () -> Unit,
    onCompleteTaskClick: () -> Unit,
    viewModel: QuestionsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    QuestionsScreen(
        onCloseClick = onCloseClick,
        onCompleteTaskClick = onCompleteTaskClick,
        onEvent = {
            viewModel.onEvent()
        },
        state = state
    )

}

@Composable
private fun QuestionsScreen(
    onCloseClick: () -> Unit,
    onCompleteTaskClick: () -> Unit,
    onEvent: () -> Unit,
    state: QuestionsState
) {

    var answerText by remember { mutableStateOf("") }
    val systemKeyboardController = LocalSoftwareKeyboardController.current
    var isCheckedToggle by remember {
        mutableStateOf(false)
    }
    var isShowClosingDialog by remember {
        mutableStateOf(false)
    }
    var isShowCompleteTaskDialog by remember {
        mutableStateOf(false)
    }
    var currentSliderPosition by remember {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier
            .background(InTouchTheme.colors.mainBlue)
            .verticalScroll(rememberScrollState())
            .clickable {
                systemKeyboardController?.hide()
                isShowClosingDialog = false
                isShowCompleteTaskDialog = false
            }
            .alpha(if (isShowClosingDialog) 0.2f else 1f)
    ) {
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 28.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(8.dp))
            TopBarArcButton(
                onClick = {
                    isShowClosingDialog = true
                },
                enabled = true,
                modifier = Modifier.align(Alignment.End)
            )
            Spacer(modifier = Modifier.height(48.dp))
            MultilineTextField(
                subtitleText = StringVO.Resource(R.string.motivates_question),
                captionText = StringVO.Resource(R.string.inscribe_motivates_question),
                value = answerText,
                modifier = Modifier.fillMaxWidth(),
                hint = StringVO.Resource(R.string.write_your_answer_here),
                onValueChange = {
                    answerText = it
                },
                isError = if (isShowCompleteTaskDialog && answerText.isBlank()) true else false,
                enabled = true,
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                keyboardActions = KeyboardActions(onDone = {
                    systemKeyboardController?.hide()
                })
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextFieldWithCheckbox(
                modifier = Modifier.fillMaxWidth(),
                subtitleText = StringVO.Resource(R.string.skills_question),
                checkboxText = StringVO.Resource(R.string.skills_leadership_answer),
                secondCheckboxText = StringVO.Resource(R.string.skills_communication_answer),
                thirdCheckboxText = StringVO.Resource(R.string.skills_problem_solving_answer),
                fourthCheckboxText = StringVO.Resource(R.string.skills_technical_expertise_answer)
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextFieldWithCheckmars(
                modifier = Modifier.fillMaxWidth(),
                subtitleText = StringVO.Resource(R.string.professional_development_question),
                captionText = StringVO.Resource(R.string.inscribe_professional_development_question),
                checkmarkText = StringVO.Resource(R.string.professional_development_attending_workshops_or_conferences_answer),
                secondCheckmarkText = StringVO.Resource(R.string.professional_development_pursuing_further_education_or_certifications),
                thirdCheckmarkText = StringVO.Resource(R.string.professional_development_participating_in_mentorship_programs),
                fourthCheckmarkText = StringVO.Resource(R.string.professional_development_joining_industry_related_associations)
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextFieldWithSliderAndDigits(
                subtitleText = StringVO.Resource(R.string.scale_questions),
                modifier = Modifier.fillMaxWidth(),
                onValueChange = { value ->
                    currentSliderPosition = value
                },
                isError = if (currentSliderPosition == 0 && isShowCompleteTaskDialog) true else false
            )
            Spacer(modifier = Modifier.height(40.dp))
            TextFieldQuestion(
                modifier = Modifier.fillMaxWidth(),
                isError = false,
                enabled = true,
                topTitleText = StringVO.Resource(R.string.navigating_professional_development),
                topSubtitleText = StringVO.Resource(R.string.skills_survey),
                topCaptionText = StringVO.Resource(R.string.skills_survey_discription),
                bottomSubtitleText = StringVO.Resource(R.string.professional_development_snapshot),
                bottomCaptionText = StringVO.Resource(R.string.professional_development_snapshot_description)
                )
            Spacer(modifier = Modifier.height(40.dp))
            Box(modifier = Modifier
                .background(color = InTouchTheme.colors.input85, shape = RoundedCornerShape(8.dp))
                .fillMaxWidth()
            ) {
                Column (modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 14.dp, bottom = 20.dp)) {
                    Text(
                        text = StringVO.Resource(R.string.dunning_kruger_effect).value(),
                        style = InTouchTheme.typography.caption1Bold,
                        color = InTouchTheme.colors.textGreen
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Image(
                        painter = painterResource(id = care.intouch.uikit.R.drawable.dunning_kruger_effect_image),
                        contentDescription = "task image on introductory questions screen",
                        contentScale = ContentScale.Fit
                    )
                }
            }
            Spacer(modifier = Modifier.height(34.dp))
            Row (
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = StringVO.Resource(R.string.share_with_therapist).value(),
                    style = InTouchTheme.typography.bodySemibold,
                    color = InTouchTheme.colors.textGreen
                )
                Spacer(modifier = Modifier.weight(1f))
                Toggle(
                    isChecked = isCheckedToggle,
                    onChange = {
                        isCheckedToggle = !isCheckedToggle
                    }
                )
            }
            Spacer(modifier = Modifier.height(36.dp))
            IntouchButton(
                onClick = {
                    isShowCompleteTaskDialog = true
                },
                isEnabled = if (isShowCompleteTaskDialog) false else true,
                modifier = Modifier.align(Alignment.CenterHorizontally),
                text = StringVO.Resource(R.string.complete_task),
                contentPadding = PaddingValues(horizontal = 51.dp, vertical = 13.dp)
            )
            Spacer(modifier = Modifier.height(40.dp))
        }
        }
    if (isShowClosingDialog) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 28.dp)
                .wrapContentHeight(Alignment.Top)
                .alpha(1F)
                .padding(top = 100.dp)
                .clip(RoundedCornerShape(20.dp)),
            content = {
                PopupQuestions(
                    inTouchButtonClick = {

                    },
                    secondaryButtonClick = {
                        isShowClosingDialog = !isShowClosingDialog
                    },
                    firstLineText = StringVO.Resource(R.string.questions_popap_closing_task_first_line),
                    secondLineText = StringVO.Resource(R.string.questions_popup_closing_task_second_line),
                    intouchButtonText = StringVO.Resource(R.string.save_in_progress_button),
                    secondaryButtonText = StringVO.Resource(R.string.discard_close_button))
            }
        )
    }
    if (isShowCompleteTaskDialog) {
        Surface(
            modifier = Modifier
                .padding(horizontal = 28.dp)
                .wrapContentHeight(Alignment.Bottom)
                .alpha(1F)
                .padding(bottom = 195.dp)
                .clip(RoundedCornerShape(20.dp)),
            content = {
                Column {
                    PopupQuestions(
                        inTouchButtonClick = {
                            isShowCompleteTaskDialog = !isShowCompleteTaskDialog
                        },
                        secondaryButtonClick = {

                        },
                        firstLineText = StringVO.Resource(R.string.questions_popap_not_all_filled),
                        intouchButtonText = StringVO.Resource(R.string.back_button),
                        secondaryButtonText = StringVO.Resource(R.string.complete_as_is_button))
                }
                }
        )
    }
}

@Preview(showBackground = true, heightDp = 1960)
@Composable
fun QuestionsScreenPreview() {
    val state = QuestionsState("")
    InTouchTheme {
        QuestionsScreen(
            onCloseClick = {},
            onCompleteTaskClick = {},
            onEvent = {},
            state = state
        )
    }
}