package com.example.verification_ui.ui

import android.app.Activity
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.verification_ui.R
import com.example.verification_ui.components.OtpInputField
import com.example.verification_ui.ui.theme.TextColor


@OptIn(ExperimentalComposeUiApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Verification() {

    val context = LocalContext.current
    var otpValue by remember { mutableStateOf("") }
    var isOtpFilled by remember { mutableStateOf(false) }
    val focusRequester = remember { FocusRequester() }
    val keyboardController = LocalSoftwareKeyboardController.current

//    (LocalView.current.context as Activity).window.statusBarColor = Color.White.toArgb()


    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { },
                navigationIcon = {
                    IconButton(onClick = { }) {
                        Icon(
                            Icons.Filled.ArrowBack,
                            contentDescription = null,
                            tint = Color.Black
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = Color.Black,
                    actionIconContentColor = Color.Black
                )
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp),
            color = Color.White
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(innerPadding)
            ) {
                Text(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(id = R.string.Verification),
                    style = MaterialTheme.typography.headlineLarge,
                    fontSize = 40.sp,
                    fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = TextColor
                )

                Spacer(modifier = Modifier.height(20.dp))
//
                Text(
                    text = stringResource(id = R.string.ver_message),
                    modifier = Modifier.fillMaxWidth(),
                    style = MaterialTheme.typography.labelLarge,
                    color = TextColor
                )

                //Otp input field
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(24.dp),
                    color = Color.White
                ) {
                    OtpInputField(
                        modifier = Modifier
                            .padding(top = 48.dp)
                            .focusRequester(focusRequester),
                        otpText = otpValue,
                        shouldCursorBlink = false,
                        onOtpModified = { value, otpFilled ->
                            otpValue = value
                            isOtpFilled = otpFilled
                            if (otpFilled) {
                                keyboardController?.hide()
                            }
                        }
                    )
                }

                Spacer(modifier = Modifier.height(30.dp))

                Button(
                    onClick = { /*TODO*/ },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ),
//                    shape = RoundedCornerShape(15.dp),
                    shape = RoundedCornerShape(topEnd = 15.dp, bottomStart = 15.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp)
//                        .shadow(5.dp)
                        .padding(start = 16.dp, end = 16.dp)
                ) {
                    Text(text = "Verify")
                }


                Spacer(modifier = Modifier.height(10.dp))

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
            }
        }
    }

}
//    Column(modifier = Modifier.padding(16.dp))
//    {
//        TopBar()
//
//        Spacer(modifier = Modifier.height(30.dp))
//
//        Text(
//            text = stringResource(id = R.string.Verification),
//            modifier = Modifier.fillMaxWidth(),
//            style = MaterialTheme.typography.headlineLarge
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))
//
//        Text(
//            text = stringResource(id = R.string.ver_message),
//            modifier = Modifier.fillMaxWidth(),
//            style = MaterialTheme.typography.labelLarge
//        )
//
//        Spacer(modifier = Modifier.height(20.dp))

//        var verificationCode by remember { mutableStateOf(listOf("", "", "", "")) }
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp)
//        ) {
//
//            for (i in 0 until 4) {
//                TextField(
//                    value = verificationCode.getOrNull(i) ?: "",
//                    onValueChange = { newValue ->
//                        verificationCode = updateList(verificationCode, i, newValue)
//                    },
//                    keyboardOptions = KeyboardOptions(
//                        keyboardType = KeyboardType.Number,
//                        imeAction = if (i == 3) ImeAction.Done else ImeAction.Next
//                    ),
//                    singleLine = true,
//                    modifier = Modifier
//                        .weight(1f)
//                        .padding(end = 8.dp)
//                        .background(MaterialTheme.colorScheme.background),
//                )
//            }
//        }

//        Spacer(modifier = Modifier.height(30.dp))
//
//        Button(
//            onClick = { /*TODO*/ },
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.Red,
//                contentColor = Color.White
//            ),
//            shape = RoundedCornerShape(15.dp),
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp)
//                .shadow(5.dp)
//        ) {
//            Text(text = "Verify")
//        }
//
//
//        Spacer(modifier = Modifier.height(10.dp))
//
//        val text = "Haven't received the code? "
//        val clickableText = " Resend it"
//
//        val annotatedString = remember {
//            AnnotatedString.Builder(text).apply {
//                pushStyle(SpanStyle(color = Color.Red))
//                append(clickableText)
//                pop()
//            }
//        }
//
//        ClickableText(
//            text = annotatedString.toAnnotatedString(),
//            onClick = { offset ->
//                if (offset >= text.length) {
//                    // Handle click on "clickableText"
//                }
//            },
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        )

//        Spacer(modifier = Modifier.height(80.dp))

//        NumericKeyboard(onKeyPress = {})
//    }
//}


//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun TopBar() {
//    TopAppBar(
//        title = {
//                Text(text = "verification")
//        },
//        navigationIcon = {
//            IconButton(onClick = { }) {
//                Icon(
//                    Icons.Filled.ArrowBack,
//                    contentDescription = null
//                )
//            }
//        }
//    )
//}

//Text filed to insert numerical

//@Composable
//fun VerificationCodeTextField(
//    code: String,
//    onCodeChange: (String) -> Unit,
//    onNextField: () -> Unit
//) {
//    val focusManager = LocalFocusManager.current
//    val focusRequester = remember { FocusRequester() }
//
//    Row {
//        repeat(4) { index ->
//            TextField(
//                value = code.getOrElse(index) { ' ' }.toString(), // Handle empty digits
//                onValueChange = {
//                    if (it.length <= 1) { // Limit to single digit
//                        onCodeChange(code.replaceRange(index, index + 1, it))
//                        if (it.isNotEmpty()) {
//                            onNextField() // Move to next field if a digit is entered
//                        }
//                    }
//                },
//                keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                singleLine = true,
//                textStyle = TextStyle(fontSize = 24.sp),
//                modifier = Modifier
//                    .width(56.dp)
//                    .padding(8.dp)
//                    .border(1.dp, Color.LightGray, shape = RoundedCornerShape(4.dp))
//                    .then(if (index == 0) Modifier.focusRequester(focusRequester) else Modifier) // Apply focusRequester to the first TextField
//                    .then(if (index == 0) Modifier.focusable() else Modifier),
//                keyboardActions = KeyboardActions(
//                    onNext = {
//                        onNextField() // Move to next field on "next" button press
//                    },
//                    onDone = {
//                        // Perform actions when done (e.g., hide keyboard, verify code)
//                        focusManager.clearFocus() // Hide keyboard when all fields are filled
//                    }
//                )
//            )
//        }
//    }
//}


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