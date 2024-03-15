package care.intouch.app.ui.utill

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import care.intouch.uikit.utill.ImageVO
import coil.compose.rememberAsyncImagePainter

class CoilPainterProvider : ImageVO.PainterProvider {
    @Composable
    override fun providePainter(
        url: String,
        placeholder: ImageVO.Resource?,
        fallback: ImageVO.Resource?
    ): Painter {
        return rememberAsyncImagePainter(
            model = url,
            placeholder = placeholder?.let { painterResource(id = it.res) },
            fallback = fallback?.let { painterResource(id = it.res) })
    }
}