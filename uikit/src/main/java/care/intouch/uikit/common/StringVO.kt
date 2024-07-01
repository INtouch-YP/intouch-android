package care.intouch.uikit.common

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

sealed class StringVO {

    @Composable
    abstract fun value(): String
    abstract fun value(context: Context): String

    data class Plain(val text: String) : StringVO() {

        @Composable
        override fun value(): String {
            return text
        }

        override fun value(context: Context): String {
            return text
        }
    }

    class Resource(
        @StringRes val resId: Int,
        vararg val params: Any
    ) : StringVO() {

        @Composable
        override fun value(): String {
            return stringResource(id = resId, formatArgs = *params)
        }

        override fun value(context: Context): String {
            return context.getString(resId, *params)
        }

        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (other !is Resource) return false

            if (resId != other.resId) return false
            if (!params.contentEquals(other.params)) return false

            return true
        }

        override fun hashCode(): Int {
            var result = resId
            result = 31 * result + params.contentHashCode()
            return result
        }
    }

}