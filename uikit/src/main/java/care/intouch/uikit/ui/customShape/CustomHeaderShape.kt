package care.intouch.uikit.ui.customShape

import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Outline
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection

class CustomHeaderShape() : androidx.compose.ui.graphics.Shape {
    override fun createOutline(
        size: Size,
        layoutDirection: LayoutDirection,
        density: Density
    ): Outline {
        return Outline.Generic(headerPath(size = size))
    }
}

fun headerPath(size: Size): Path {
    return Path().apply {
        moveTo(0f, 0f)

        lineTo(size.width, 0f)

        lineTo(size.width, size.height - size.height / 4)

        arcTo(
            rect = Rect(
                left = 0f,
                top = size.height / 2,
                right = size.width,
                bottom = size.height
            ),
            startAngleDegrees = 0f,
            sweepAngleDegrees = 180f,
            forceMoveTo = false
        )

        close()
    }
}