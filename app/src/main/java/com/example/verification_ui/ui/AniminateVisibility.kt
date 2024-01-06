package com.example.verification_ui.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun AniminateVisibility(
    modifier: Modifier = Modifier
) {
    var isVisible by remember { mutableStateOf(true) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .size(300.dp)
                .background(Color.Red)
        )

        AnimatedVisibility(visible = isVisible) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.Blue)
            )
        }

        Button(onClick = { isVisible = !isVisible }, modifier.padding(top = 32.dp)) {
            Text(text = "Toggle")
        }

    }

}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun PreviewAniminateVisibility() {
    AniminateVisibility()
}