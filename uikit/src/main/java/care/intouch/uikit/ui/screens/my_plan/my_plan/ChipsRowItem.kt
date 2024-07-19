package care.intouch.uikit.ui.screens.my_plan.my_plan

import care.intouch.uikit.common.StringVO

data class ChipsRowItem(
    val title: StringVO,
    val onItemClick: () -> Unit
)
