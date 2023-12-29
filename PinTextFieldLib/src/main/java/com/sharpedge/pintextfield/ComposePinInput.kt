package com.sharpedge.pintextfield

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


enum class ComposePinInputStyle {
    BOX,
    UNDERLINE
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
public fun ComposePinInput(
    value: String,
    onValueChange: (String) -> Unit,
    maxSize: Int = 4,
    mask: Char? = null,
    isError: Boolean = false,
    onPinEntered: ((String) -> Unit)? = null,
    cellShape: Shape = RoundedCornerShape(4.dp),
    fontColor: Color = Color.LightGray,
    cellBorderColor: Color = Color.Gray,
    rowPadding: Dp = 8.dp,
    cellPadding: Dp = 16.dp,
    cellSize: Dp = 50.dp,
    cellBorderWidth: Dp = 1.dp,
    textFontSize: TextUnit = 20.sp,
    focusedCellBorderColor: Color = Color.DarkGray,
    errorBorderColor: Color = Color.Red,
    cellBackgroundColor: Color = Color.Transparent,
    cellColorOnSelect: Color = Color.Transparent,
    borderThickness: Dp = 2.dp,
    style: ComposePinInputStyle = ComposePinInputStyle.BOX
) {

    val keyboardController = LocalSoftwareKeyboardController.current
    val focusedState = remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }

    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        BasicTextField(
            value = value,
            onValueChange = { text ->
                if (text.length <= maxSize) {
                    onValueChange(text)
                    if (text.length == maxSize) {
                        onPinEntered?.invoke(text)
                    }
                }
            },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(onDone = {
                keyboardController?.hide()
            }),
            modifier = Modifier
                .alpha(0.01f)
                .onFocusChanged {
                    focusedState.value = it.isFocused
                }
                .focusRequester(focusRequester),

            textStyle = TextStyle.Default.copy(color = Color.Transparent),

            )

        // UI for the Pin
        val boxWidth = cellSize
        Row(
            horizontalArrangement = Arrangement.spacedBy(cellPadding),
            modifier = Modifier
                .padding(rowPadding)
        ) {
            repeat(maxSize) { index ->
                val isActiveBox = focusedState.value && index == value.length

                if (style == ComposePinInputStyle.BOX) {
                    // Box Style Pin field logic starts from here
                    Box(
                        modifier = Modifier
                            .size(cellSize)
                            .background(
                                color = if (index < value.length) cellColorOnSelect else cellBackgroundColor,
                                shape = cellShape
                            )
                            .border(
                                width = cellBorderWidth,
                                color = when {
                                    isError -> errorBorderColor
                                    isActiveBox -> focusedCellBorderColor
                                    else -> cellBorderColor
                                },
                                shape = cellShape
                            )
                            .clickable(
                                indication = null, // Disable ripple effect
                                interactionSource = remember { MutableInteractionSource() }
                            ) { focusRequester.requestFocus() }
                    ) {
                        if (index < value.length) {
                            val displayChar = mask ?: value[index]
                            Text(
                                text = displayChar.toString(),
                                modifier = Modifier.align(Alignment.Center),
                                fontSize = textFontSize,
                                color = fontColor
                            )
                        }
                    }


                } else {
                    // Underline style logic here
                    Box(
                        modifier = Modifier
                            .size(boxWidth, cellSize + borderThickness)
                            .background(color = if (index < value.length) cellColorOnSelect else cellBackgroundColor)
                            .clickable(
                                indication = null, // Disable ripple effect
                                interactionSource = remember { MutableInteractionSource() }
                            ) { focusRequester.requestFocus() }
                    ) {
                        Canvas(modifier = Modifier.fillMaxSize(), onDraw = {
                            drawLine(
                                color = when {
                                    isError -> errorBorderColor
                                    isActiveBox -> focusedCellBorderColor
                                    else -> cellBorderColor
                                },
                                start = Offset(x = 0f, y = size.height - borderThickness.toPx()),
                                end = Offset(
                                    x = size.width,
                                    y = size.height - borderThickness.toPx()
                                ),
                                strokeWidth = borderThickness.toPx()
                            )
                        })

                        if (index < value.length) {
                            val displayChar = mask ?: value[index]
                            val lineHeightDp: Dp = with(LocalDensity.current) {
                                textFontSize.toDp()
                            }
                            Text(
                                text = displayChar.toString(),
                                modifier = Modifier
                                    .align(Alignment.TopCenter)
                                    .padding(top = (cellSize - lineHeightDp) / 2),
                                fontSize = textFontSize,
                                color = fontColor
                            )
                        }
                    }
                }
            }
        }
    }
}
