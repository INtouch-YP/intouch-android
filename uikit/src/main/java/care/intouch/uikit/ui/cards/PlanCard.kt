package care.intouch.uikit.ui.cards

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.common.StringVO
import care.intouch.uikit.theme.InTouchTheme
import care.intouch.uikit.ui.AccentChips
import care.intouch.uikit.ui.toggle.Toggle

@Composable
fun PlanCard(
    modifier: Modifier = Modifier,
    chipText: StringVO,
    chipTextColor: Color = InTouchTheme.colors.textGreen.copy(alpha = 0.4f),
    chipColors: Color = InTouchTheme.colors.accentBeige,
    dateText: String = "",
    dateTextColor: Color = InTouchTheme.colors.textBlue.copy(alpha = 0.5f),
    dateTextStyle: TextStyle = InTouchTheme.typography.bodySemibold,
    text: String,
    textColor: Color = InTouchTheme.colors.textGreen,
    textTextStyle: TextStyle = InTouchTheme.typography.bodyBold,
    toggleIsChecked: Boolean = false,
    backgroundColor: Color = InTouchTheme.colors.mainBlue,
    isSettingsClicked: Boolean,
    onClickSetting: (Boolean) -> Unit,
    dropdownMenuItemsList: List<DropdownMenuItemsPlanCard>,
    dropdownMenuItemIconColor: Color = InTouchTheme.colors.textBlue.copy(alpha = 0.5f),
    dropdownMenuItemTextColor: Color = InTouchTheme.colors.textBlue.copy(alpha = 0.5f),
    dropdownMenuItemTextStyle: TextStyle = InTouchTheme.typography.caption2Semibold,
    dropdownMenuItemColorSelect: Color = InTouchTheme.colors.accentGreen30,
    onClickToggle: (Boolean) -> Unit
) {
    Column(
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(backgroundColor)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 10.dp, start = 14.dp, end = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AccentChips(
                modifier = Modifier,
                text = chipText,
                selected = true,
                selectedColor = chipColors,
                unselectedColorText = chipTextColor
            ) {}
            Text(
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 23.dp),
                text = dateText,
                color = dateTextColor,
                style = dateTextStyle
            )

            Box {
                if (dropdownMenuItemsList.isNotEmpty()) {
                    var selectedItemIndex by remember {
                        mutableIntStateOf(-1)
                    }



                    DropdownMenu(
                        modifier = Modifier
                            .clip(RoundedCornerShape(5.dp))
                            .background(InTouchTheme.colors.input),
                        offset = DpOffset(x = -(8.dp), y = -(28.dp)),
                        expanded = isSettingsClicked,
                        onDismissRequest = { onClickSetting.invoke(!isSettingsClicked) }) {
                        dropdownMenuItemsList.forEachIndexed { index, dropdownMenuItemsPlanCard ->
                            val interactionSource = remember { MutableInteractionSource() }
                            val isPressed by interactionSource.collectIsPressedAsState()

                            DropdownMenuItem(modifier = Modifier
                                .height(40.dp)
                                .width(144.dp)
                                .clickable(
                                    interactionSource = interactionSource,
                                    indication = rememberRipple(color = dropdownMenuItemColorSelect)
                                ) {}
                                .background(
                                    if (isPressed && index == selectedItemIndex) {
                                        dropdownMenuItemColorSelect
                                    } else {
                                        InTouchTheme.colors.input
                                    }
                                ), interactionSource = interactionSource,
                                leadingIcon = {
                                    Icon(
                                        tint = dropdownMenuItemIconColor,
                                        painter = painterResource(id = dropdownMenuItemsPlanCard.icon),
                                        contentDescription = ""
                                    )
                                }, text = {
                                    Text(
                                        modifier = Modifier.padding(start = 4.dp),
                                        text = dropdownMenuItemsPlanCard.text,
                                        color = dropdownMenuItemTextColor,
                                        style = dropdownMenuItemTextStyle
                                    )
                                }, onClick = {
                                    selectedItemIndex = index
                                    dropdownMenuItemsPlanCard.onClick()
                                }, contentPadding = PaddingValues(horizontal = 24.dp)
                            )
                        }
                    }
                }

                Image(
                    modifier = Modifier.clickable {
                        onClickSetting.invoke(!isSettingsClicked)
                    },
                    painter = painterResource(
                        id =
                        if (dropdownMenuItemsList.isEmpty()) {
                            R.drawable.icon_trash_light
                        } else {
                            R.drawable.icon_elipsis_vertical
                        }
                    ),
                    contentDescription = "dropdown menu or trash card",
                )
            }
        }
        Image(
            modifier = Modifier.fillMaxHeight(0.62f),
            painter = painterResource(R.drawable.illustration_plan_card),
            contentDescription = "illustration plan card"
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 14.dp)
                .padding(top = 4.dp, bottom = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.fillMaxWidth(0.7f),
                text = text,
                color = textColor,
                style = textTextStyle,
                overflow = TextOverflow.Ellipsis,
                maxLines = 2
            )
            Toggle(isChecked = toggleIsChecked) {
                onClickToggle(it)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPlanCards() {
    var toggleIsChecked by remember {
        mutableStateOf(false)
    }
    val menuItems: List<DropdownMenuItemsPlanCard> =
        listOf(DropdownMenuItemsPlanCard("Duplicate", R.drawable.icon_duplicate) {},
            DropdownMenuItemsPlanCard("Clear", R.drawable.icon_small_trash) {})
    InTouchTheme {
        var onClickSetting by remember { mutableStateOf(false) }
        PlanCard(
            modifier = Modifier
                .padding(28.dp),
            chipText = StringVO.Plain("Done"),
            dateText = "May, 15  2023",
            text = "Socratic dialogue Learning...\n" + "Lorem ipsum dolor sit amet ",
            toggleIsChecked = toggleIsChecked,
            onClickToggle = { toggleIsChecked = !toggleIsChecked },
            onClickSetting = { onClickSetting = it },
            dropdownMenuItemsList = menuItems,
            isSettingsClicked = onClickSetting
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewPlanCardsEmptyDropDownMenu() {
    var toggleIsChecked by remember {
        mutableStateOf(false)
    }
    val menuItems: List<DropdownMenuItemsPlanCard> =
        listOf()
    InTouchTheme {
        var onClickSetting by remember { mutableStateOf(false) }
        PlanCard(
            modifier = Modifier
                .padding(28.dp),
            chipText = StringVO.Plain("Done"),
            dateText = "May, 15  2023",
            text = "Socratic dialogue Learning...\n" + "Lorem ipsum dolor sit amet ",
            toggleIsChecked = toggleIsChecked,
            onClickToggle = { toggleIsChecked = !toggleIsChecked },
            onClickSetting = { onClickSetting = it },
            dropdownMenuItemsList = menuItems,
            isSettingsClicked = onClickSetting
        )
    }
}