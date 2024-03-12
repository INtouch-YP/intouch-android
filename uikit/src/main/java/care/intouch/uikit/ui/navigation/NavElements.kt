package care.intouch.uikit.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
fun TopBarArcButton(
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier.clickable { onClick.invoke() },
        contentAlignment = Alignment.Center
    ) {
       Icon(
           painter = painterResource(id = R.drawable.arc_rectangle),
           contentDescription = null,
           tint = InTouchTheme.colors.accentColorGreen50
       )
        Icon(
            painter = painterResource(id = R.drawable.icon_close),
            contentDescription = null,
            tint = InTouchTheme.colors.inputColor
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
            modifier = Modifier.padding(bottom = 4.dp)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun NavBottomComplexElementPreview() {
    NavBottomComplexElement(
        onClick = { /*TODO*/ },
        text = "Home",
        painter = painterResource(id = R.drawable.icon_home),
        focusTint = InTouchTheme.colors.mainColorGreen,
    )
}

// Navigation UI
@Composable
fun CustomTopBar(
    onBackArrowClick: () -> Unit,
    onCloseButtonClick: () -> Unit,
    title: String
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(98.dp)
            .background(InTouchTheme.colors.inputColor),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(start = 24.dp, top = 30.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.icon_arrow_left),
                contentDescription = null,
                tint = InTouchTheme.colors.mainColorGreen,
                modifier = Modifier
                    .clickable { onBackArrowClick.invoke() },
                )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 25.dp)
        ) {
            Text(
                text = title,
                style = InTouchTheme.typography.titleLargeTypography
            )
        }

        Box(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(end = 24.dp, top = 20.dp)
        ) {
            TopBarArcButton(
                onClick = onCloseButtonClick
            )
        }
    }
}

@Composable
fun CustomBottomNavBar(
    onFocusTint: Color = InTouchTheme.colors.mainColorGreen,
    outFocusTint: Color = InTouchTheme.colors.mainColorGreen40
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
            onClick = { selectedIconIndex.intValue = ElementId.HOME_ID.id },
            text = "Home",
            painter = painterResource(id = R.drawable.icon_home),
            focusTint = if (selectedIconIndex.intValue == ElementId.HOME_ID.id) onFocusTint
            else outFocusTint,
            modifier = Modifier
                .constrainAs(homeTag) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )
        
        NavBottomComplexElement(
            onClick = { selectedIconIndex.intValue = ElementId.ADDITIONAL_ID.id },
            text = "Additional",
            painter = painterResource(id = R.drawable.icon_additional),
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
        ) { selectedIconIndex.intValue = ElementId.PLUS_ID.id }

        NavBottomComplexElement(
            onClick = { selectedIconIndex.intValue = ElementId.MY_PROGRESS_ID.id },
            text = "My progress",
            painter = painterResource(id = R.drawable.icon_progress),
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
            onClick = { selectedIconIndex.intValue = ElementId.MY_PLAN_ID.id },
            text = "My Plan",
            painter = painterResource(id = R.drawable.icon_plan),
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

enum class ElementId(val id: Int) {
    HOME_ID(0), MY_PROGRESS_ID(1), PLUS_ID(2), MY_PLAN_ID(3), ADDITIONAL_ID(4);
}

