package care.intouch.uikit.ui.toggle

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import care.intouch.uikit.theme.InTouchTheme

@Composable
fun Toggle(
    modifier: Modifier = Modifier,
    handleWidth: Dp = 27.dp,
    handleHeight: Dp = 27.dp,
    trackWidth: Dp = 51.dp,
    trackHeight: Dp = 31.dp,
    isChecked: Boolean,
    onChange: (Boolean) -> Unit,
) {
    Row(
        modifier = modifier
            .width(trackWidth)
            .height(trackHeight)
            .clip(CircleShape)
            .background(
                if (isChecked) InTouchTheme.colors.mainGreen
                else InTouchTheme.colors.unableElementDark
            ),
        horizontalArrangement = if (isChecked) Arrangement.End else Arrangement.Start
    ) {
        Box(
            modifier = modifier
                .padding(2.dp)
                .width(handleWidth)
                .height(handleHeight)
                .drawShadow(
                    alpha = 0.15f,
                    offsetX = 0.dp,
                    offsetY = 3.dp,
                    radius = handleHeight.value,
                    blurRadius = 8.dp
                )
                .clip(CircleShape)
                .background(
                    if (isChecked) InTouchTheme.colors.mainBlue
                    else InTouchTheme.colors.unableElementLight
                )
                .align(Alignment.CenterVertically)
                .clickable {
                    onChange.invoke(!isChecked)
                }
        )
    }
}

fun Modifier.drawShadow(
    color: Color = Color.Black,
    alpha: Float = 0.06f,
    radius: Float = 0f,
    offsetX: Dp = 0.dp,
    offsetY: Dp = 0.dp,
    blurRadius: Dp = 1.dp,
) = this.drawBehind {
    this.drawIntoCanvas {
        val paint = Paint()
        paint.asFrameworkPaint().apply {
            this.color = color.toArgb()
            this.setShadowLayer(
                blurRadius.toPx(),
                offsetX.toPx(),
                offsetY.toPx(),
                color.copy(alpha = alpha).toArgb()
            )
        }
        it.drawCircle(
            Offset((this.size.width + offsetX.value) / 2f, (this.size.height + offsetY.value) / 2f),
            radius,
            paint
        )
    }
}


@Preview
@Composable
fun TogglePreview() {
    var isChecked by remember { mutableStateOf(true) }

    InTouchTheme {
        Toggle(
            isChecked = isChecked
        ) {
            isChecked = it
        }
    }
}
