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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.verification_ui.R

@OptIn(ExperimentalMaterial3Api::class)
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

        VerificationCodeTextField(
            code = "",
            onCodeChange = {}
        )

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun VerificationCodeTextField(
    code: String,
    onCodeChange: (String) -> Unit
) {
    // Use a Row to arrange the 4 text fields horizontally
    Row(
        horizontalArrangement = Arrangement.SpaceEvenly
//        modifier = Modifier.padding(start = 16.dp)
    ) {
        repeat(4) { index ->
            TextField(
                value = code.getOrElse(index) { ' ' }.toString(),
                onValueChange = {
                    if (it.length <= 1) { // Limit to single digit
                        onCodeChange(code.replaceRange(index, index + 1, it))
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
                colors = TextFieldDefaults.textFieldColors(
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


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DefaultPreview() {
    Verification()
}