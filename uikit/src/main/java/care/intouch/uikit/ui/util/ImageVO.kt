package care.intouch.uikit.ui.util

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter

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
        val url: String,
        val placeholder: Resource? = null,
        val fallback: Resource? = null,
    ) : ImageVO() {

        @Composable
        override fun painter(): Painter {
            return rememberAsyncImagePainter(
                model = url,
                placeholder = placeholder?.let { painterResource(id = it.res) },
                fallback = fallback?.let { painterResource(id = it.res) })
        }
    }

}