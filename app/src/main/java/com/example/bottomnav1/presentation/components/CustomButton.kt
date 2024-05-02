package com.example.bottomnav1.presentation.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomButton(
    text: String,
    clickButton: () -> Unit,
    buttonWidth: Int = 275, // Default button width
    buttonHeight: Int = 60, // Default button height
    cornerRadius: Int = 30 // Default corner radius
) {
    Button(
        onClick = clickButton,
        shape = RoundedCornerShape(cornerRadius.dp),
        modifier = Modifier
            .padding(horizontal = 10.dp)
            .width(buttonWidth.dp)
            .height(buttonHeight.dp)
    ) {
        Text(
            text = text,
            fontSize = 20.sp
        )
    }
}
