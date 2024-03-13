package care.intouch.uikit.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme

// UI elements
@Composable
fun NavBottomBarPlusButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .size(70.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(InTouchTheme.colors.mainColorGreen)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick.invoke() }
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.icon_plus_large),
            contentDescription = null,
            tint = InTouchTheme.colors.inputColor
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NavBottomBarPlusButtonPreview() {
    InTouchTheme {
        NavBottomBarPlusButton(
            onClick = {}
        )
    }
}

@Composable
fun TopBarArcButton(
    onClick: () -> Unit,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick.invoke() }
            ),
        contentAlignment = Alignment.Center
    ) {
       Icon(
           painter = painterResource(id = R.drawable.arc_rectangle),
           contentDescription = null,
           tint = if (enabled) InTouchTheme.colors.accentColorGreen else InTouchTheme.colors.accentColorGreen50
       )
        Icon(
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = null,
            tint = InTouchTheme.colors.inputColor
        )
    }
}

@Composable
@Preview(showBackground = true)
fun TopBarArcButtonPreview() {
    InTouchTheme {
        TopBarArcButton(
            onClick = {},
            enabled = false
        )
    }
}

@Composable
fun NavBottomComplexElement(
    onClick: () -> Unit,
    text: String,
    painter: Painter,
    focusTint: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(75.dp)
            .height(56.dp)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onClick.invoke() }
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom
    ) {
        Icon(
            painter = painter,
            contentDescription = null,
            tint = focusTint,
            modifier = modifier
        )
        Text(
            text = text,
            color = focusTint,
            style = InTouchTheme.typography.tabBarTypography,
            modifier = Modifier.padding(bottom = 4.dp),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NavBottomComplexElementPreview() {
    InTouchTheme {
        NavBottomComplexElement(
            onClick = { /*TODO*/ },
            text = "Home",
            painter = painterResource(id = R.drawable.icon_home),
            focusTint = InTouchTheme.colors.mainColorGreen,
        )
    }
}

// Navigation UI
@Composable
fun CustomTopBar(
    onBackArrowClick: () -> Unit,
    onCloseButtonClick: () -> Unit,
    title: String,
    enabledArcButton: Boolean,
    addBackArrowButton: Boolean,
    addCloseButton: Boolean
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(InTouchTheme.colors.inputColor),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {

        if (addBackArrowButton) {
            Box(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .size(44.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_arrow_left),
                    contentDescription = null,
                    tint = InTouchTheme.colors.mainColorGreen,
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .clickable(
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() },
                            onClick = { onBackArrowClick.invoke() }
                        ),
                )
            }
        } else {
            Box(
                modifier = Modifier
                    .padding(start = 28.dp)
                    .size(44.dp)
                    .background(Color.Transparent)
            )
        }

        Text(
            text = title,
            style = InTouchTheme.typography.titleLargeTypography,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .weight(1f),
            textAlign = TextAlign.Center
        )

        if (addCloseButton) {
            TopBarArcButton(
                onClick = onCloseButtonClick,
                enabled = enabledArcButton,
                modifier = Modifier.padding(end = 28.dp)
            )
        } else {
            Box(
                modifier = Modifier
                    .padding(end = 28.dp)
                    .size(47.dp)
                    .background(Color.Transparent)
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CustomTopBarPreview() {
    InTouchTheme {
        CustomTopBar(
            onBackArrowClick = { /*TODO*/ },
            onCloseButtonClick = { /*TODO*/ },
            title = "Title Large",
            enabledArcButton = false,
            addBackArrowButton = true,
            addCloseButton = true
        )
    }
}

@Composable
fun CustomBottomNavBar(
    onFocusTint: Color,
    outFocusTint: Color,
    firstItemText: String,
    secondItemText: String,
    thirdItemText: String,
    fourthItemText: String,
    firstItemImage: Painter,
    secondItemImage: Painter,
    thirdItemImage: Painter,
    fourthItemImage: Painter,
    firstItemClick: () -> Unit,
    secondItemClick: () -> Unit,
    thirdItemClick: () -> Unit,
    fourthItemClick: () -> Unit,
    onPlusItemClick: () -> Unit
) {
    ConstraintLayout(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {

        val selectedIconIndex = rememberSaveable {
            mutableIntStateOf(0)
        }

        val (homeTag, progressTag, plusTag, myPlanTag, additionalTag, box) = createRefs()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp)
                .clip(RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp))
                .background(Color.White)
                .constrainAs(box) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )

        NavBottomComplexElement(
            onClick = {
                selectedIconIndex.intValue = ElementId.HOME_ID.id
                firstItemClick.invoke()
            },
            text = firstItemText,
            painter = firstItemImage,
            focusTint = if (selectedIconIndex.intValue == ElementId.HOME_ID.id) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(homeTag) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        
        NavBottomComplexElement(
            onClick = {
                selectedIconIndex.intValue = ElementId.ADDITIONAL_ID.id
                fourthItemClick.invoke()
            },
            text = fourthItemText,
            painter = fourthItemImage,
            focusTint = if (selectedIconIndex.intValue == ElementId.ADDITIONAL_ID.id) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(additionalTag) {
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                }
        )

        NavBottomBarPlusButton(
            modifier = Modifier.constrainAs(plusTag) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                bottom.linkTo(parent.bottom)
                top.linkTo(parent.top)
            }
        ) {
            selectedIconIndex.intValue = ElementId.PLUS_ID.id
            onPlusItemClick.invoke()
        }

        NavBottomComplexElement(
            onClick = {
                selectedIconIndex.intValue = ElementId.MY_PROGRESS_ID.id
                secondItemClick.invoke()
            },
            text = secondItemText,
            painter = secondItemImage,
            focusTint = if (selectedIconIndex.intValue == ElementId.MY_PROGRESS_ID.id) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(progressTag) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(homeTag.end)
                    end.linkTo(plusTag.start)
                }
        )
        
        NavBottomComplexElement(
            onClick = {
                selectedIconIndex.intValue = ElementId.MY_PLAN_ID.id
                thirdItemClick.invoke()
            },
            text = thirdItemText,
            painter = thirdItemImage,
            focusTint = if (selectedIconIndex.intValue == ElementId.MY_PLAN_ID.id) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(myPlanTag) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(plusTag.end)
                    end.linkTo(additionalTag.start)
                }
        )
    }
}

@Composable
@Preview(showBackground = true)
fun CustomBottomNavBarPreview() {
    InTouchTheme {
        CustomBottomNavBar(
            onFocusTint = InTouchTheme.colors.mainColorGreen,
            outFocusTint = InTouchTheme.colors.mainColorGreen40,
            firstItemText = "Home",
            secondItemText = "My progress",
            thirdItemText = "My plan",
            fourthItemText = "Additional",
            firstItemImage = painterResource(id = R.drawable.icon_home),
            secondItemImage = painterResource(id = R.drawable.icon_progress),
            thirdItemImage = painterResource(id = R.drawable.icon_plan),
            fourthItemImage = painterResource(id = R.drawable.icon_additional),
            firstItemClick = {},
            secondItemClick = {},
            thirdItemClick = {},
            fourthItemClick = {},
            onPlusItemClick = {}
        )
    }
}

enum class ElementId(val id: Int) {
    HOME_ID(0), MY_PROGRESS_ID(1), PLUS_ID(2), MY_PLAN_ID(3), ADDITIONAL_ID(4);
}

