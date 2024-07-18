package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun DiaryPlaceHolder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                start = 28.dp,
                end = 28.dp,
                top = 16.dp,
            )
    ) {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(bottom = 50.dp),
            color = InTouchTheme.colors.mainBlue, shape = RoundedCornerShape(20.dp)
        ) { }

        Column(verticalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(
                    id = R.string.empty_diary
                ),
                style = InTouchTheme.typography.bodySemibold,
                color = InTouchTheme.colors.textGreen,
                modifier = Modifier.padding(horizontal = 35.dp, vertical = 22.dp)
            )

            Image(
                modifier = Modifier
                    .padding(start = 65.dp)
                    .fillMaxHeight(),
                painter = painterResource(
                    id = care.intouch.uikit.R.drawable.pic_arrow_new_diary
                ),
                contentDescription = "the arrow that points on the creation icon"
            )
        }
    }
}

@Composable
@Preview(
    showBackground = true,
    heightDp = 180
)
fun DiaryPlaceHolderPreview() {
    InTouchTheme {
        DiaryPlaceHolder(
        )
    }
}