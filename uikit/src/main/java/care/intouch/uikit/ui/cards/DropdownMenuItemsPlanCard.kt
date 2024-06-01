package care.intouch.uikit.ui.cards

import android.graphics.drawable.Icon

data class DropdownMenuItemsPlanCard(
    val text: String,
    val icon: Int,
    val onClick: () -> Unit
)
