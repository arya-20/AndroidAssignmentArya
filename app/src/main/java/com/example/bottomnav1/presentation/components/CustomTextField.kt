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
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import com.example.bottomnav1.presentation.theme.DarkBlue


@Composable
fun CustomTextField(
    modifier: Modifier,
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
            .border(1.dp, Color.Gray, RoundedCornerShape(27.dp))
            .semantics { contentDescription = hintText },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.Gray,
            backgroundColor = Color.Transparent,
            cursorColor = DarkBlue,
            errorCursorColor = Color.White
        ),
        textStyle = LocalTextStyle.current.copy(color = Color.Black)
    )



    if (errorPresent) {
        Text(
            modifier = Modifier.padding(start = 10.dp, top = 2.dp),
            text = errorMessage,
            color = Color.White
        )
    }
}
