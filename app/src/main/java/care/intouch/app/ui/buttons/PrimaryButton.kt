package care.intouch.app.ui.buttons

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import care.intouch.uikit.R
import care.intouch.uikit.theme.InTouchTheme
@Composable
fun PrimaryButton(
    onClick: () -> Unit,
    mode : ButtonModeEnum
){
    Button(
        modifier = Modifier
            .width(262.dp)
            .height(69.dp)
            .padding(5.dp),
        border = if (mode == ButtonModeEnum.ABLESTROKE)
        {
        BorderStroke(1.dp,InTouchTheme.colors.textColorGreen40)
        } else {
               BorderStroke(0.dp,InTouchTheme.colors.inputColor)
               },
        colors = when (mode){
            ButtonModeEnum.ABLEWHITE -> ButtonColors(
                containerColor = InTouchTheme.colors.inputColor,
                contentColor = InTouchTheme.colors.textColorGreen,
                disabledContainerColor = InTouchTheme.colors.mainColorGreen,
                disabledContentColor = InTouchTheme.colors.mainColorGreen,
            )
            ButtonModeEnum.UNABLE -> ButtonColors(
                containerColor = InTouchTheme.colors.unableElementsColorLight,
                contentColor = InTouchTheme.colors.textColorGreen40,
                disabledContainerColor = InTouchTheme.colors.mainColorGreen,
                disabledContentColor = InTouchTheme.colors.mainColorGreen,
            )
            ButtonModeEnum.ABLE -> ButtonColors(
                containerColor = InTouchTheme.colors.mainColorGreen,
                contentColor = InTouchTheme.colors.inputColor,
                disabledContainerColor = InTouchTheme.colors.mainColorGreen,
                disabledContentColor = InTouchTheme.colors.mainColorGreen,
            )
            ButtonModeEnum.ABLESTROKE -> ButtonColors(
                containerColor = InTouchTheme.colors.inputColor,
                contentColor = InTouchTheme.colors.textColorGreen,
                disabledContainerColor = InTouchTheme.colors.mainColorGreen,
                disabledContentColor = InTouchTheme.colors.mainColorGreen,
            )
            ButtonModeEnum.DEFAULTNOSTROKE -> ButtonColors(
                containerColor = InTouchTheme.colors.inputColor,
                contentColor = InTouchTheme.colors.inputColor,
                disabledContainerColor = InTouchTheme.colors.mainColorGreen,
                disabledContentColor = InTouchTheme.colors.mainColorGreen,
            )
        }, onClick = {onClick.invoke()}
    )
    {
        Text(text = "Call to action",style = InTouchTheme.typography.titleMediumTypography)
    }
}
@Composable
@Preview(showBackground = true)
fun PreviewPrimaryButton(){
    InTouchTheme {
        PrimaryButton(onClick = { }, mode = ButtonModeEnum.ABLESTROKE)
    }
}