package com.example.verification_ui.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.verification_ui.R

@Composable
fun Verification() {

    Column(modifier = Modifier.padding(16.dp))
    {
        TopBar()

        Spacer(modifier = Modifier.height(30.dp))

        Text(
            text = stringResource(id = R.string.Verification),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = stringResource(id = R.string.ver_message),
            modifier = Modifier.fillMaxWidth(),
            style = MaterialTheme.typography.labelLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        var verificationCode by remember { mutableStateOf(listOf("", "", "", "")) }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {
            for (i in 0 until 4) {
                TextField(
                    value = verificationCode.getOrNull(i) ?: "",
                    onValueChange = {
                        newValue -> verificationCode = updateList(verificationCode, i, newValue) },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number,
                        imeAction = if (i == 3) ImeAction.Done else ImeAction.Next
                    ),
                    singleLine = true,
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                        .background(MaterialTheme.colorScheme.background),
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        val text = "Haven't received the code? "
        val clickableText = " Resend it"

        val annotatedString = remember {
            AnnotatedString.Builder(text).apply {
                pushStyle(SpanStyle(color = Color.Red))
                append(clickableText)
                pop()
            }
        }

        ClickableText(
            text = annotatedString.toAnnotatedString(),
            onClick = { offset ->
                if (offset >= text.length) {
                    // Handle click on "clickableText"
                }
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(10.dp))


        Button(
            onClick = { /*TODO*/ },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Red,
                contentColor = Color.White
            ),
            shape = RoundedCornerShape(15.dp),
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .shadow(5.dp)
        ) {
            Text(text = "Verify")
        }

        Spacer(modifier = Modifier.height(80.dp))

        NumericKeyboard(onKeyPress = {})
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar() {
    TopAppBar(
        title = {
//                Text(text = "verification")
        },
        navigationIcon = {
            IconButton(onClick = { }) {
                Icon(
                    Icons.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

private fun updateList(list: List<String>, index: Int, value: String): List<String> {
    return list.toMutableList().also { it[index] = value }
}

@Composable
private fun getNextEmptyIndex(list: List<String>): Int {
    return list.indexOfFirst { it.isEmpty() }.takeIf { it != -1 } ?: (list.size - 1)
}

@Composable
fun VerificationCodeTextField(
    code: String,
    onCodeChange: (String) -> Unit
) {
    // Use a Row to arrange the 4 text fields horizontally
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        repeat(4) { index ->
            val textFieldValue = remember { mutableStateOf(code.getOrElse(index) { ' ' }.toString()) }

            LaunchedEffect(code) {
                textFieldValue.value = code.getOrElse(index) { ' ' }.toString()
            }

            TextField(
                value = textFieldValue.value,
                onValueChange = {
                    if (it.length <= 1) { // Limit to single digit
                        if (index < code.length) {
                            onCodeChange(code.replaceRange(index, index + 1, it))
                        } else {
                            onCodeChange(code + it)
                        }
                    }
                },
                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                singleLine = true,
                textStyle = TextStyle(fontSize = 24.sp),
                modifier = Modifier
                    .width(86.dp)
                    .padding(8.dp)
                    .background(color = Color.Transparent),
                shape = RoundedCornerShape(15.dp),
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Blue,
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                )
            )
        }
    }
}

@Composable
fun NumericKeyboard(onKeyPress: (Char) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, bottom = 16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { onKeyPress('1') }) {
                Text("1")
            }
            Button(onClick = { onKeyPress('2') }) {
                Text("2")
            }
            Button(onClick = { onKeyPress('3') }) {
                Text("3")
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { onKeyPress('4') }) {
                Text("4")
            }
            Button(onClick = { onKeyPress('5') }) {
                Text("5")
            }
            Button(onClick = { onKeyPress('6') }) {
                Text("6")
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Button(onClick = { onKeyPress('7') }) {
                Text("7")
            }
            Button(onClick = { onKeyPress('8') }) {
                Text("8")
            }
            Button(onClick = { onKeyPress('9') }) {
                Text("9")
            }
        }
        Row(
            horizontalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Button(onClick = { onKeyPress('0') }) {
                Text("0")
            }
            Button(
                onClick = {}, // Handle backspace functionality here
//                modifier = Modifier.weight(1f),
                enabled = false // Disable until implemented
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.outline_backspace_24),
                    contentDescription = "Backspace"
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Verification()
}