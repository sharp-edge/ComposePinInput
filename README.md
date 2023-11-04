# PinTextField - A Jetpack Compose Library

Welcome to the `PinTextField` library for Android, built with Kotlin and Jetpack Compose using Material Design 3 principles. This customizable and versatile library allows you to easily integrate pin entry fields into your Compose UI, ideal for pin code, OTP, or password input.


The GIF below demonstrates the `PinEntryTextField` in a live application scenario. Following the GIF, you will find the example code that shows how to implement this functionality using Jetpack Compose.


<img src="pintextfield_1.gif" width="300" />

Using the `PinEntryTextField` into your Jetpack Compose layout is straightforward. The example below demonstrates a basic setup. In this setup, we define a `PinTextFieldPreview` composable function that maintains the state of the entered pin. The `PinEntryTextField` composable takes the current `pin` value, a lambda to handle value changes, and an action to perform when the pin has been completely entered. It also allows you to customize the cell size and apply a predefined style, such as a box around each digit.

```kotlin
@Composable
fun PinTextFieldPreview() {
    // State to hold the value of the pin
    var pin by remember { mutableStateOf("") }
    // Local context to show a toast message
    val context = LocalContext.current
  
    PinEntryTextField(
        value = pin,
        onValueChange = {
            pin = it // Update the state when the value changes
        },
        cellSize = 70.dp, // size (height and width of each Cell
        onPinEntered = { // when the PIN has been entered get notified
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = PinFieldStyle.BOX
    )
}
```

## Input Masking with Asterisks

Here is a version that masks the input, displaying asterisks (*) instead of the actual characters as the user types:

<img src="pintextfield_2.gif" width="300" />


```kotlin
@Composable
fun PinTextFieldPreview() {
    // State to hold the value of the pin
    var pin by remember { mutableStateOf("") }
    // Local context to show a toast message
    val context = LocalContext.current
  
    PinEntryTextField(
        value = pin,
        mask= '*', // Masks the input with asterisks
        onValueChange = {
            pin = it
        },
        cellSize = 70.dp,
        onPinEntered = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = PinFieldStyle.BOX
    )
}
```
## Custom Cell Border Colors
Enhance the visual appeal of your `PinEntryTextField` by customizing the cell border colors, including the color change when a cell is focused:

<img src="pintextfield_3.gif" width="300" />

```kotlin
@Composable
fun PinTextFieldPreview() {
    var pin by remember { mutableStateOf("") }
    val context = LocalContext.current
  
    PinEntryTextField(
        value = pin,
        mask= '*',
        cellBorderColor = Color.Blue, // Just pass the color here for the cell/box border
        focusedCellBorderColor = Color.Magenta, // when the box is focused it's color can be customized here
        onValueChange = {
            pin = it
        },
        cellSize = 70.dp,
        onPinEntered = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = PinFieldStyle.BOX
    )
}
```

# Underline Style Customization
You can change the shape of `PinEntryTextField` instead of Boxes simple underlines are also supported
For a subtler look, `PinEntryTextField` supports an underline style instead of boxes. The following example demonstrates this style:


<img src="pintextfield_4.gif" width="300" />


```kotlin
@Composable
fun PinTextFieldPreview() {
    var pin by remember { mutableStateOf("") }
    val context = LocalContext.current

  // Underline style with custom border colors
    PinEntryTextField(
        value = pin,
        mask= '*',
        cellBorderColor = Color.DarkGray, // Just pass the color here for the cell/box border
        focusedCellBorderColor = Color.Blue, // when the box is focused it's color can be customized here
        onValueChange = {
            pin = it
        },
        cellSize = 70.dp,
        onPinEntered = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = PinFieldStyle.UNDERLINE // Apply the underline style
    )
}
```
# Dynamic Background and Font Colors
Customize the bfocu ackground color and font color

<img src="pintextfield_5.gif" width="300" />

```kotlin
@Composable
fun PinTextFieldPreview() {
    var pin by remember { mutableStateOf("") }
    val context = LocalContext.current

    // Box style with dynamic background and font colors
    PinEntryTextField(
        value = pin,
        mask= '*',
        cellBorderColor = Color.DarkGray, // Just pass the color here for the cell/box border
        focusedCellBorderColor = Color.Blue, // when the box is focused it's color can be customized here
        cellColorOnSelect = Color.Cyan // This will change the color once focus is on the cell
        fontColor = Color.Blue, // This is how font color can be changed
        onValueChange = {
            pin = it
        },
        cellSize = 70.dp,
        onPinEntered = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = PinFieldStyle.BOX // // Retaining the box style
    )
}
```

# Flexibility in Number of Cells and Mask Character
`PinEntryTextField` is highly flexible, allowing you to define the number of cells and choose a custom mask character, as illustrated below:

<img src="pintextfield_6.gif" width="300" />


```kotlin
@Composable
fun PinTextFieldPreview() {
    var pin by remember { mutableStateOf("") }
    val context = LocalContext.current

  // Custom number of cells and mask character
    PinEntryTextField(
        value = pin,
        mask = '⚫', // // Choose any character as the mask
        maxSize = 6, // // Define the number of cells, 4 are default if this property is not used
        cellSize = 45.dp, // default size is 50.dp, this property is optional
        onValueChange = {
            pin = it
        },
        onPinEntered = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = PinFieldStyle.BOX // // Using the box style
    )
}
```
