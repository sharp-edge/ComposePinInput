package com.sharpedge.pintextfieldusage

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.sharpedge.pintextfield.ComposePinInputStyle
import com.sharpedge.pintextfield.ComposePinInput
import com.sharpedge.pintextfieldusage.ui.theme.PinTextFieldUsageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PinTextFieldUsageTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    Column {
                        PinTextFieldPreview()
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


@Composable
fun PinTextFieldPreview() {
    var pin by remember { mutableStateOf("") }
    val context = LocalContext.current


    //Text(text = "Hello world")

    Spacer(modifier = Modifier.size(20.dp))
    Spacer(modifier = Modifier.size(20.dp))


// 1
    ComposePinInput(
        value = pin,
        onValueChange = {
            pin = it
        },
        cellSize = 60.dp,
        onPinEntered = {
            Log.d("Pin_entered", "Pin = $it")
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = ComposePinInputStyle.BOX
    )

// 2
    ComposePinInput(
        value = pin,
        mask= '*',
        onValueChange = {
            pin = it
        },
        cellSize = 70.dp,
        onPinEntered = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = ComposePinInputStyle.BOX
    )



// 3
    ComposePinInput(
        value = pin,
        mask= '*',
        cellBorderColor = Color.Blue,
        focusedCellBorderColor = Color.Magenta,
        onValueChange = {
            pin = it
        },
        cellSize = 70.dp,
        onPinEntered = {
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = ComposePinInputStyle.BOX
    )

// 4
//    PinTextField(
//        value = pin,
//        mask= '*',
//        cellBorderColor = Color.DarkGray,
//        focusedCellBorderColor = Color.Blue,
//        onValueChange = {
//            pin = it
//        },
//        //boxHeight = 70.dp,
//        onPinEntered = {
//            Log.d("Pin_entered", "Pin = $it")
//            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
//        },
//
//        style = PinFieldStyle.UNDERLINE
//    )


// 5
//    PinTextField(
//        value = pin,
//        mask= '*',
//        cellBorderColor = Color.DarkGray,
//        focusedCellBorderColor = Color.Blue,
//        cellColorOnSelect = Color.Cyan,
//        fontColor = Color.Blue,
//        onValueChange = {
//            pin = it
//        },
//        //boxHeight = 70.dp,
//        onPinEntered = {
//            Log.d("Pin_entered", "Pin = $it")
//            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
//        },
//
//        style = PinFieldStyle.BOX
//    )


    // 6
    ComposePinInput(
        value = pin,
        onValueChange = {
            pin = it
        },
        mask = '⚫',
        maxSize = 6,
        cellSize = 45.dp,
        onPinEntered = {
            Log.d("Pin_entered", "Pin = $it")
            Toast.makeText(context, "$it", Toast.LENGTH_SHORT).show()
        },

        style = ComposePinInputStyle.BOX
    )

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PinTextFieldUsageTheme {
        PinTextFieldPreview()
    }
}