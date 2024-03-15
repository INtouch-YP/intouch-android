package care.intouch.uikit.utill

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource

sealed class ImageVO {

    @Composable
    abstract fun painter(): Painter

    data class Resource(
        @DrawableRes val res: Int,
        val tint: Color? = null
    ) : ImageVO() {

        @Composable
        override fun painter(): Painter {
            return painterResource(id = res)
        }
    }

    data class Url(
        val painterProvider: PainterProvider,
        val url: String,
        val placeholder: Resource? = null,
        val fallback: Resource? = null,
    ) : ImageVO() {

        @Composable
        override fun painter(): Painter {
            return painterProvider.providePainter(
                url = url,
                placeholder = placeholder,
                fallback = fallback
            )
        }
    }

    interface PainterProvider {
        @Composable
        fun providePainter(
            url: String,
            placeholder: Resource?,
            fallback: Resource?,
        ): Painter
    }

}