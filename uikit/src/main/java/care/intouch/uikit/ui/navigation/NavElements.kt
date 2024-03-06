package care.intouch.uikit.ui.navigation

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
            .clickable { onClick.invoke() },
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
fun NavBottomDefaultElementText(
    text: String,
    onFocusColor: Color,
    outFocusColor: Color,
    modifier: Modifier = Modifier
) {
    Text(
        modifier = modifier
            .width(64.dp)
            .wrapContentWidth(align = Alignment.CenterHorizontally),
        text = text,
        color = onFocusColor,
        style = InTouchTheme.typography.tabBarTypography
    )
}

@Composable
fun NavBottomDefaultElement(
    onClick: () -> Unit,
    painter: Painter,
    onFocusTint: Color,
    outFocusTint: Color,
    modifier: Modifier = Modifier,
) {
    Icon(
        painter = painter,
        contentDescription = null,
        tint = onFocusTint,
        modifier = modifier.wrapContentWidth().clickable { onClick.invoke() }
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
fun CustomBottomNavBar() {
    ConstraintLayout(
        modifier = Modifier
            .height(70.dp)
            .fillMaxWidth()
            .background(Color.Transparent)
    ) {

        val (homeTag, progressTag, plusTag, myPlanTag, additionalTag, box,
            homeTextTag, progressTextTag, myPlanTextTag, additionalTextTag) = createRefs()

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

        NavBottomDefaultElement(
            onClick = { /*TODO*/ },
            painter = painterResource(id = R.drawable.icon_home),
            onFocusTint = InTouchTheme.colors.mainColorGreen,
            outFocusTint = InTouchTheme.colors.mainColorGreen40,
            modifier = Modifier
                .constrainAs(homeTag) {
                    bottom.linkTo(box.bottom)
                    top.linkTo(box.top)
                    start.linkTo(parent.start)
                }
                .padding(start = 23.dp)
        )

        NavBottomDefaultElement(
            onClick = { /*TODO*/ },
            painter = painterResource(id = R.drawable.icon_additional),
            onFocusTint = InTouchTheme.colors.mainColorGreen,
            outFocusTint = InTouchTheme.colors.mainColorGreen40,
            modifier = Modifier
                .constrainAs(additionalTag) {
                    bottom.linkTo(box.bottom)
                    top.linkTo(box.top)
                    end.linkTo(box.end)
                }
                .padding(end = 23.dp)
        )

        NavBottomBarPlusButton(
            modifier = Modifier.constrainAs(plusTag) {
                start.linkTo(box.start)
                end.linkTo(box.end)
                bottom.linkTo(box.bottom)
                top.linkTo(parent.top)
            },
            onClick = {}
        )

        NavBottomDefaultElement(
            onClick = { /*TODO*/ },
            painter = painterResource(id = R.drawable.icon_progress),
            onFocusTint = InTouchTheme.colors.mainColorGreen,
            outFocusTint = InTouchTheme.colors.mainColorGreen40,
            modifier = Modifier
                .constrainAs(progressTag) {
                    bottom.linkTo(box.bottom)
                    top.linkTo(box.top)
                    end.linkTo(plusTag.start)
                    start.linkTo(homeTag.end)
                }
        )

        NavBottomDefaultElement(
            onClick = { /*TODO*/ },
            painter = painterResource(id = R.drawable.icon_plan),
            onFocusTint = InTouchTheme.colors.mainColorGreen,
            outFocusTint = InTouchTheme.colors.mainColorGreen40,
            modifier = Modifier.constrainAs(myPlanTag) {
                bottom.linkTo(box.bottom)
                top.linkTo(box.top)
                start.linkTo(plusTag.end)
                end.linkTo(additionalTag.start)
            }
        )

        NavBottomDefaultElementText(
            text = "Home",
            modifier = Modifier
                .constrainAs(homeTextTag) {
                    top.linkTo(homeTag.bottom)
                    start.linkTo(homeTag.start)
                    end.linkTo(homeTag.end)
                }
                .padding(start = 23.dp, top = 2.dp),
            onFocusColor = InTouchTheme.colors.mainColorGreen,
            outFocusColor = InTouchTheme.colors.mainColorGreen40
        )

        NavBottomDefaultElementText(
            text = "Additional",
            modifier = Modifier
                .constrainAs(additionalTextTag) {
                    top.linkTo(additionalTag.bottom)
                    start.linkTo(additionalTag.start)
                    end.linkTo(additionalTag.end)
                }
                .padding(end = 23.dp, top = 2.dp),
            onFocusColor = InTouchTheme.colors.mainColorGreen,
            outFocusColor = InTouchTheme.colors.mainColorGreen40
        )

        NavBottomDefaultElementText(
            text = "My progress",
            modifier = Modifier
                .constrainAs(progressTextTag) {
                    top.linkTo(progressTag.bottom)
                    start.linkTo(progressTag.start)
                    end.linkTo(progressTag.end)
                }
                .padding(top = 2.dp),
            onFocusColor = InTouchTheme.colors.mainColorGreen,
            outFocusColor = InTouchTheme.colors.mainColorGreen40
        )

        NavBottomDefaultElementText(
            text = "My plan",
            modifier = Modifier
                .constrainAs(myPlanTextTag) {
                    top.linkTo(myPlanTag.bottom)
                    start.linkTo(myPlanTag.start)
                    end.linkTo(myPlanTag.end)
                }
                .padding(top = 2.dp),
            onFocusColor = InTouchTheme.colors.mainColorGreen,
            outFocusColor = InTouchTheme.colors.mainColorGreen40
        )
    }
}
