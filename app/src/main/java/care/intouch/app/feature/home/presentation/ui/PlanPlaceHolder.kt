package care.intouch.app.feature.home.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.app.R
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun PlanPlaceHolder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 28.dp, end = 28.dp, top = 16.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(InTouchTheme.colors.mainBlue),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.empty_plan),
            style = InTouchTheme.typography.bodySemibold,
            color = InTouchTheme.colors.textGreen,
            modifier = Modifier.padding(horizontal = 36.dp, vertical = 22.dp)
        )
        Image(
            modifier = Modifier
                .padding(top = 8.dp, bottom = 28.dp)
                .fillMaxWidth(0.5F),
            painter = painterResource(id = care.intouch.uikit.R.drawable.illustration_empty),
            contentDescription = "empty plan placeholder"
        )
    }
}

@Composable
@Preview(
    showBackground = true,
    heightDp = 360
)
fun PlanPlaceHolderPreview() {
    InTouchTheme {
        PlanPlaceHolder(
        )
    }

}