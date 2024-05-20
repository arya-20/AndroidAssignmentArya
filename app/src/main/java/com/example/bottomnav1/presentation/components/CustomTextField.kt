package com.example.bottomnav1.presentation.components
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.LocalTextStyle
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun CustomTextField(
    hintText: String,
    text: String,
    onValueChange: (String) -> Unit,
    errorMessage: String,
    errorPresent: Boolean,
    isPasswordField: Boolean = false

    ) {
    TextField(
        value = text,
        onValueChange = onValueChange,
        isError = errorPresent,
        singleLine = true,
        label = {
            Text(hintText)
        },
        visualTransformation = if (isPasswordField) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = if (isPasswordField) KeyboardType.Password else KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        modifier = Modifier.padding(10.dp)
            .border(1.dp, Color.Black, RoundedCornerShape(27.dp)),
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Black,
            backgroundColor = Color.Transparent,
            cursorColor = Color.Black,
            errorCursorColor = Color.Red
        ),
        textStyle = LocalTextStyle.current.copy(color = Color.Black)
    )



    if (errorPresent) {
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 2.dp),
            text = errorMessage,
            color = Color.Red
        )
    }
}
