package care.intouch.app.feature.diary.presentation.ui.emotionScreen

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionDesc
import care.intouch.app.feature.diary.presentation.ui.emotionScreen.models.EmotionTask
import care.intouch.uikit.R
import care.intouch.uikit.common.ImageVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.cards.EmotionCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun EmotionsPager(
    taskList: List<EmotionTask>,
    onClick: (page: Int) -> Unit,
) {
    val pagerState = rememberPagerState(
        pageCount = { taskList.size },
        initialPage = 0
    )
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(start = 140.dp, end = 110.dp),
        pageSpacing = 10.dp,
        modifier = Modifier
            .padding(top = 32.dp),
    ) { page ->
        val scale = if (page == pagerState.currentPage) 1.0f else 0.8f
        onClick.invoke(pagerState.currentPage)
        EmotionCard(
            modifier = Modifier,
            emotion = (if (scale == 1.0f) taskList[page].bigImageVO else taskList[page].imageVO)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun PlanPagerPreview() {
    InTouchTheme {
        val taskList = listOf(
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_terrible),
                bigImageVO = ImageVO.Resource(R.drawable.icon_terrible_big),
                emotionDesc = EmotionDesc.TERRIBLE
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_bad),
                bigImageVO = ImageVO.Resource(R.drawable.icon_bad_big),
                emotionDesc = EmotionDesc.BAD
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_okay),
                bigImageVO = ImageVO.Resource(R.drawable.icon_okey_big),
                emotionDesc = EmotionDesc.OKAY
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_good),
                bigImageVO = ImageVO.Resource(R.drawable.icon_good_big),
                emotionDesc = EmotionDesc.GOOD
            ),
            EmotionTask(
                imageVO = ImageVO.Resource(R.drawable.icon_great),
                bigImageVO = ImageVO.Resource(R.drawable.icon_great_big),
                emotionDesc = EmotionDesc.GREAT
            ),
        )
        EmotionsPager(
            taskList = taskList, onClick = {}
        )
    }
}